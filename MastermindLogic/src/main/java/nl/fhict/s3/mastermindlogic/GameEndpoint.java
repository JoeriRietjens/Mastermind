package nl.fhict.s3.mastermindlogic;

import java.net.http.WebSocket;
import java.util.*;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import nl.fhict.s3.mastermindlogic.entity.*;


@ServerEndpoint("/game")
public class GameEndpoint {
    private static final List<Session> players = new ArrayList<>();
    private static final Map<UUID,List<Session>> games = new HashMap<>();

    private Application application = Application.getInstance();

    @OnOpen
    public void onOpen(Session session) {
        players.add(session);
        logMessage(session.getId(), "connected, #sessions: ", Integer.toString(players.size()));
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        logMessage(session.getId(), "received", message);
        handleClientMessage(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        logMessage(session.getId(), "closed", reason.getReasonPhrase());
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        logMessage(session.getId(), "error", cause.getMessage());
        cause.printStackTrace(System.err);
    }

    private void handleClientMessage(String serializedMessage, Session session) {
        WebSocketMessage message = parseMessage(serializedMessage);
        if(message == null) {
            return;
        }

        WebSocketMessageOperation operation = message.getOperation();
        UUID gameId = message.getGameId();

        if(operation != null && gameId != null) {
            switch(operation) {
                case REGISTER_GAME:
                    registerGameOperation(session);
                    break;
                case UNREGISTER_GAME:
                    unregisterGameOperation(gameId);
                    break;
                case JOIN_GAME:
                    joinGameOperation(gameId, session);
                    break;
                case LEAVE_GAME:
                    leaveGameOperation(gameId, session);
                    break;
                case SUBMIT_CODE:
                    submitCodeOperation(gameId, message, session);
                    break;
                case SUBMIT_GUESS:
                    submitGuessOperation(gameId, session, message);
                    break;
                case GET_EMPTY_ROW:
                    getEmptyRowOperation(gameId, session);
                    break;
                default:
                    cannotParseMessage(serializedMessage);
                    break;
            }
        }
    }

    private WebSocketMessage parseMessage(String serializedMessage) {
        Gson gson = new Gson();
        WebSocketMessage wsMessage = null;

        try {
            wsMessage = gson.fromJson(serializedMessage, WebSocketMessage.class);
        } catch (JsonSyntaxException ex) {
            cannotParseMessage(serializedMessage);
            return null;
        }

        return wsMessage;
    }

    private void sendMessage(Session toSession, UUID gameId, WebSocketMessageOperation operation, Object content) {
        Gson gson = new Gson();
        WebSocketMessage returnMessage = new WebSocketMessage();
        returnMessage.setGameId(gameId);
        returnMessage.setOperation(operation);
        returnMessage.setContent(gson.toJson(content));
        String jsonReturnMessage = gson.toJson(returnMessage);
        toSession.getAsyncRemote().sendText(jsonReturnMessage);
    }

    private void logMessage(String sessionId, String action, String message) {
        System.out.println(String.format("[session %s] %s: %s", sessionId, action, message));
    }

    private void cannotParseMessage(String message) {
        System.err.println("[wsServer] cannot parse: " + message);
    }

    private void registerGameOperation(Session session) {
        Game game = application.getOpenGameOrNew();
        int playerID = -1;
        if(game.getPlayer1() == null) {
            game.setPlayer1(new Player(0));
            playerID = 0;
        } else if(game.getPlayer2() == null) {
            game.setPlayer2(new Player(1));
            playerID = 1;
        } else {
            //TODO: error
        }
        
        assert playerID != -1;

        games.put(game.getId(), new ArrayList<Session>());
        games.get(game.getId()).add(session);
        
        // Return gameID to user
        sendMessage(session, game.getId(), WebSocketMessageOperation.REGISTER_GAME, game.getId().toString());
        
        // return playerID to user
        sendMessage(session, game.getId(), WebSocketMessageOperation.JOIN_GAME, Integer.toString(playerID));
    }

    private void unregisterGameOperation(UUID gameId) {
        // TODO: this should send a message to all sessions in property to leave game then remove from games
    }

    private void joinGameOperation(UUID gameId, Session session) {
        if(games.get(gameId) != null) {
            Game game = application.getGameById(gameId);
            games.get(game.getId()).add(session);
            
            // TODO: return playerID to user
        }
    }

    private void leaveGameOperation(UUID gameId, Session session) {
        // TODO: implement leaving a game
    }

    private void submitCodeOperation(UUID gameId, WebSocketMessage message, Session session) {
        Gson gson = new Gson();
        if(games.get(gameId) != null) {
            int playerId = message.getPlayerId();
            int opponentId = Math.abs(playerId-1);
            EPinColour[] code = gson.fromJson(message.getContent(), EPinColour[].class);
            Player opponent = application.getGameById(gameId).getPlayer(opponentId);
            if (opponent != null) {
                opponent.getBoard().setCode(code);
            } else {
                logMessage(session.getId(), "error", "no opponent has joined yet");
            }
        } else {
            logMessage(session.getId(), "error", "could not find game, gameId: " + gameId);
        }
    }

    private void submitGuessOperation(UUID gameId, Session session, WebSocketMessage message) {
        if(games.get(gameId) != null) {
            Gson gson = new Gson();
            Row row = gson.fromJson(message.getContent(), Row.class);
            Game currentGame = application.getGameById(gameId);
            if (currentGame == null) {
                logMessage(session.getId(), "error", "could not find game in app");
            } else {
                Row returnRow = currentGame.getPlayer(message.getPlayerId()).getBoard().checkRow(row);
                // for(Session s : games.get(gameId).getSessions()) {
                //     s.getAsyncRemote().sendText(jsonReturnMessage);
                // } // This is for the multiplayer, to be implemented
                sendMessage(session, gameId, WebSocketMessageOperation.SUBMIT_GUESS, returnRow);
            }
        } else {
            logMessage(session.getId(), "error", "could not find game, gameId null: " + gameId);
        }
    }

    private void getEmptyRowOperation(UUID gameId, Session session) {
        sendMessage(session, gameId, WebSocketMessageOperation.GET_EMPTY_ROW, new Row(0));
    }
}

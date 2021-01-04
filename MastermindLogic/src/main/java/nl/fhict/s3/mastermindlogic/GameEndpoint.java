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

import nl.fhict.s3.mastermindlogic.entity.Application;
import nl.fhict.s3.mastermindlogic.entity.EPinColour;
import nl.fhict.s3.mastermindlogic.entity.Game;
import nl.fhict.s3.mastermindlogic.entity.Row;


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
                    getEmptyRowOperation(session);
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

    private void logMessage(String sessionId, String action, String message) {
        System.out.println(String.format("[session %s] %s: %s", sessionId, action, message));
    }

    private void cannotParseMessage(String message) {
        System.err.println("[wsServer] cannot parse: " + message);
    }

    private void registerGameOperation(Session session) {
        Game game = new Game();
        application.newGame(game);
        games.put(game.getId(), new ArrayList<Session>());
        games.get(game.getId()).add(session);
        
        // Return gameID to user
        Gson gson = new Gson();
        WebSocketMessage returnMessage = new WebSocketMessage();
        returnMessage.setGameId(game.getId());
        returnMessage.setOperation(WebSocketMessageOperation.REGISTER_GAME);
        returnMessage.setContent(gson.toJson(game.getId().toString()));
        String jsonReturnMessage = gson.toJson(returnMessage);
        session.getAsyncRemote().sendText(jsonReturnMessage);
    }

    private void unregisterGameOperation(UUID gameId) {
        // TODO: this should send a message to all sessions in property to leave game then remove from games
    }

    private void joinGameOperation(UUID gameId, Session session) {
        if(games.get(gameId) != null) {
            Game game = application.getGameById(gameId);
            games.get(game.getId()).add(session);
        }
    }

    private void leaveGameOperation(UUID gameId, Session session) {
        // TODO: implement leaving a game
    }

    private void submitCodeOperation(UUID gameId, WebSocketMessage message, Session session) {
        Gson gson = new Gson();
        // TODO: finish, make sure code gets set to right player
        if(games.get(gameId) != null) {
            int playerId = message.getPlayerId();
            int opponentId = 1;
            EPinColour[] code = gson.fromJson(message.getContent(), EPinColour[].class);
            application.getGameById(gameId).getPlayer(opponentId).getBoard().setCode(code);
        } else {
            logMessage(session.getId(), "error", "could not find game, gameId: " + gameId);
        }
    }

    private void submitGuessOperation(UUID gameId, Session session, WebSocketMessage message) {
        if(games.get(gameId) != null) {
            Gson gson = new Gson();
            WebSocketMessage returnMessage = new WebSocketMessage();
            returnMessage.setOperation(WebSocketMessageOperation.SUBMIT_GUESS);
            returnMessage.setGameId(gameId);

            Row row = gson.fromJson(message.getContent(), Row.class);
            Game currentGame = application.getGameById(gameId);
            if (currentGame == null) {
                logMessage(session.getId(), "error", "could not find game in app");
            } else {
                Row returnRow = currentGame.getPlayer(message.getPlayerId()).getBoard().checkRow(row);
                returnMessage.setContent(gson.toJson(returnRow));
                String jsonReturnMessage = gson.toJson(returnMessage);
                // for(Session s : games.get(gameId).getSessions()) {
                //     s.getAsyncRemote().sendText(jsonReturnMessage);
                // } // This is for the multiplayer, to be implemented
                session.getAsyncRemote().sendText(jsonReturnMessage);
            }


        } else {
            logMessage(session.getId(), "error", "could not find game, gameId null: " + gameId);
        }
    }

    private void getEmptyRowOperation(Session session) {
        Gson gson = new Gson();
        String emptyRow = gson.toJson(new Row(0));
        WebSocketMessage message = new WebSocketMessage();
        message.setOperation(WebSocketMessageOperation.GET_EMPTY_ROW);
        message.setContent(emptyRow);
        String jsonMessage = gson.toJson(message);
        session.getAsyncRemote().sendText(jsonMessage);
    }
}

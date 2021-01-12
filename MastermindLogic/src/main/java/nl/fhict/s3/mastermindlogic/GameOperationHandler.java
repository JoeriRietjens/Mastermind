package nl.fhict.s3.mastermindlogic;

import java.util.*;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import nl.fhict.s3.mastermindlogic.entity.*;

public class GameOperationHandler {
    private static final Map<UUID,List<Session>> games = new HashMap<>();

    private static Application application = Application.getInstance();

    public static WebSocketMessage parseMessage(String serializedMessage) {
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

    private static void sendMessage(Session toSession, int playerId, UUID gameId, WebSocketMessageOperation operation, Object content) {
        Gson gson = new Gson();
        WebSocketMessage returnMessage = new WebSocketMessage();
        returnMessage.setGameId(gameId);
        returnMessage.setPlayerId(playerId);
        returnMessage.setOperation(operation);
        returnMessage.setContent(gson.toJson(content));
        String jsonReturnMessage = gson.toJson(returnMessage);
        toSession.getAsyncRemote().sendText(jsonReturnMessage);
    }

    public static void logMessage(String sessionId, String action, String message) {
        System.out.println(String.format("[session %s] %s: %s", sessionId, action, message));
    }

    public static void cannotParseMessage(String message) {
        System.err.println("[wsServer] cannot parse: " + message);
    }

    public static void registerGameOperation(Session session) {
        Game game = application.getOpenGameOrNew();
        int playerId = -1;
        if(game.getPlayer1() == null) {
            game.setPlayer1(new Player(0));
            playerId = 0;
        } else if(game.getPlayer2() == null) {
            game.setPlayer2(new Player(1));
            playerId = 1;
        } else {
            //TODO: error
        }
        
        assert playerId != -1;

        if(!games.containsKey(game.getId())) {
            games.put(game.getId(), new ArrayList<>());
        }
        games.get(game.getId()).add(session);
        
        // Return gameId and playerId to user
        sendMessage(session, playerId, game.getId(), WebSocketMessageOperation.REGISTER_GAME, game.getId().toString());
        for (Session s :
                games.get(game.getId())) {
            if(s != session){
                sendMessage(s, playerId, game.getId(), WebSocketMessageOperation.JOIN_GAME, null);
            }
        }
    }

    public static void unregisterGameOperation(UUID gameId) {
        // TODO: this should send a message to all sessions in property to leave game then remove from games
    }

    public static void joinGameOperation(UUID gameId, Session session) {
        if(games.get(gameId) != null) {
            Game game = application.getGameById(gameId);
            games.get(game.getId()).add(session);
            
            // TODO: return playerID to user
        }
    }

    public static void leaveGameOperation(UUID gameId, Session session) {
        // TODO: implement leaving a game
    }

    public static void submitCodeOperation(UUID gameId, WebSocketMessage message, Session session) {
        Gson gson = new Gson();
        if(games.get(gameId) != null) {
            int playerId = message.getPlayerId();
            int opponentId = Math.abs(playerId-1);
            EPinColour[] code = gson.fromJson(message.getContent(), EPinColour[].class);
            Player opponent = application.getGameById(gameId).getPlayer(opponentId);
            if (opponent != null) {
                opponent.getBoard().setCode(code);
                for(Session s : games.get(gameId)) {
                    sendMessage(s, message.getPlayerId(), gameId, WebSocketMessageOperation.SUBMIT_CODE, null);
                }
            } else {
                logMessage(session.getId(), "error", "no opponent has joined yet");
            }
        } else {
            logMessage(session.getId(), "error", "could not find game, gameId: " + gameId);
        }
    }

    public static void submitGuessOperation(UUID gameId, Session session, WebSocketMessage message) {
        if(games.get(gameId) != null) {
            Gson gson = new Gson();
            Row row = gson.fromJson(message.getContent(), Row.class);
            Game currentGame = application.getGameById(gameId);
            if (currentGame == null) {
                logMessage(session.getId(), "error", "could not find game in app");
            } else {
                Row returnRow = currentGame.getPlayer(message.getPlayerId()).getBoard().checkRow(row);
                for(Session s : games.get(gameId)) {
                    sendMessage(s, message.getPlayerId(), gameId, WebSocketMessageOperation.SUBMIT_GUESS, returnRow);
                }
            }
        } else {
            logMessage(session.getId(), "error", "could not find game, gameId null: " + gameId);
        }
    }

    public static void getEmptyRowOperation(UUID gameId, Session session, WebSocketMessage message) {
        sendMessage(session, message.getPlayerId(), gameId, WebSocketMessageOperation.GET_EMPTY_ROW, new Row(0));
    }

    public static void getCodeOperation (UUID gameId, Session session,int playerId)
    {
        if(games.get(gameId) != null) {
            Gson gson = new Gson();
            WebSocketMessage returnMessage = new WebSocketMessage();
            returnMessage.setOperation(WebSocketMessageOperation.GET_CODE);
            returnMessage.setGameId(gameId);
            returnMessage.setPlayerId(playerId);

            Game game= application.getGameById(gameId);

            EPinColour[] code= game.getPlayer(playerId).getBoard().getCode();

            String content =gson.toJson(code);

            returnMessage.setContent(content);
            String jsonReturnMessage = gson.toJson(returnMessage);
            session.getAsyncRemote().sendText(jsonReturnMessage);
        }
    }
}

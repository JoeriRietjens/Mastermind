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

    @OnOpen
    public void onOpen(Session session) {
        players.add(session);
        GameOperationHandler.logMessage(session.getId(), "connected, #sessions: ", Integer.toString(players.size()));
    }
    
    @OnMessage
    public void onMessage(String message, Session session) {
        GameOperationHandler.logMessage(session.getId(), "received", message);
        handleClientMessage(message, session);
    }

    @OnClose
    public void onClose(CloseReason reason, Session session) {
        GameOperationHandler.logMessage(session.getId(), "closed", reason.getReasonPhrase());
    }

    @OnError
    public void onError(Throwable cause, Session session) {
        GameOperationHandler.logMessage(session.getId(), "error", cause.getMessage());
        cause.printStackTrace(System.err);
    }

    private void handleClientMessage(String serializedMessage, Session session) {
        WebSocketMessage message = GameOperationHandler.parseMessage(serializedMessage);
        if(message == null) {
            return;
        }

        WebSocketMessageOperation operation = message.getOperation();
        UUID gameId = message.getGameId();

        if(operation != null && gameId != null) {
            switch(operation) {
                case REGISTER_GAME:
                    GameOperationHandler.registerGameOperation(session);
                    break;
                case UNREGISTER_GAME:
                    GameOperationHandler.unregisterGameOperation(gameId);
                    break;
                case JOIN_GAME:
                    GameOperationHandler.joinGameOperation(gameId, session);
                    break;
                case LEAVE_GAME:
                    GameOperationHandler.leaveGameOperation(gameId, session);
                    break;
                case SUBMIT_CODE:
                    GameOperationHandler.submitCodeOperation(gameId, message, session);
                    break;
                case SUBMIT_GUESS:
                    GameOperationHandler.submitGuessOperation(gameId, session, message);
                    break;
                case GET_EMPTY_ROW:
                    GameOperationHandler.getEmptyRowOperation(gameId, session, message);
                    break;
                case RESTART_GAME:
                    GameOperationHandler.restartGameOperation(gameId);
                    break;
                default:
                    GameOperationHandler.cannotParseMessage(serializedMessage);
                    break;
            }
        }
    }

    /*private WebSocketMessage parseMessage(String serializedMessage) {
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

    private void sendMessage(Session toSession, int playerId, UUID gameId, WebSocketMessageOperation operation, Object content) {
        Gson gson = new Gson();
        WebSocketMessage returnMessage = new WebSocketMessage();
        returnMessage.setGameId(gameId);
        returnMessage.setPlayerId(playerId);
        returnMessage.setOperation(operation);
        returnMessage.setContent(gson.toJson(content));
        String jsonReturnMessage = gson.toJson(returnMessage);
        toSession.getAsyncRemote().sendText(jsonReturnMessage);
    }

    private void logMessage(String sessionId, String action, String message) {
        System.out.println(String.format("[session %s] %s: %s", sessionId, action, message));
    }*/

    private void cannotParseMessage(String message) {
        System.err.println("[wsServer] cannot parse: " + message);
    }


}

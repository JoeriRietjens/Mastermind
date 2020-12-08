package nl.fhict.s3.mastermindlogic;

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


@ServerEndpoint("/game")
public class GameEndpoint {
    private static final List<Session> players = new ArrayList<>();
    private static final Map<String,List<Session>> games = new HashMap<>();

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
        String property = message.getProperty();

        if(operation != null && property != null && !"".equals(property)) {
            switch(operation) {
                case REGISTER_GAME:
                    registerGameOperation(property);
                    break;
                case UNREGISTER_GAME:
                    unregisterGameOperation(property);
                    break;
                case JOIN_GAME:
                    joinGameOperation(property, session);
                    break;
                case LEAVE_GAME:
                    leaveGameOperation(property, session);
                    break;
                case SUBMIT_CODE:
                    submitCodeOperation(property, session, serializedMessage);
                    break;
                case SUBMIT_GUESS:
                    submitGuessOperation(property, session, serializedMessage);
                    break;
                case GET_EMPTY_ROW:
                    getEmptyRowOperation(property, session, serializedMessage);
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

    private void registerGameOperation(String property) {
        if(games.get(property) == null) {
            games.put(property, new ArrayList<>());
        }
    }

    private void unregisterGameOperation(String property) {
        // TODO: this should send a message to all sessions in property to leave game then remove from games
    }

    private void joinGameOperation(String property, Session session) {
        if(games.get(property) != null) {
            games.get(property).add(session);
        }
    }

    private void leaveGameOperation(String property, Session session) {
        if(games.get(property) != null) {
            games.get(property).remove(session);
        }
    }

    private void submitCodeOperation(String property, Session session, String serializedMessage) {
        // TODO: fill in
    }

    private void submitGuessOperation(String property, Session session, String serializedMessage) {
        // TODO: fill in
    }

    private void getEmptyRowOperation(Session session) {
        Gson gson = new Gson();
        String emptyRow = gson.toJson(new Row(0));
        session.getAsyncRemote().sendText(emptyRow);
    }
}

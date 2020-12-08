package communicatorserver;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import communicatorshared.*;
import mastermindlogic.Application;
import mastermindlogic.Game;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;

// https://github.com/jetty-project/embedded-jetty-websocket-examples/tree/master/javax.websocket-example/src/main/java/org/eclipse/jetty/demo

/**
 * Server-side implementation of Communicator using WebSockets for communication.
 * 
 * This code is based on example code from:
 * https://github.com/jetty-project/embedded-jetty-websocket-examples/blob/
 * master/javax.websocket-example/src/main/java/org/eclipse/jetty/
 * demo/EventServerSocket.java
 *
 * @author Nico Kuijpers, based on example project
 */

@ServerEndpoint(value="/communicator/")
public class CommunicatorServerWebSocket {
    
    // All sessions
    private static final List<Session> sessions = new ArrayList<>();
    
    // Map each property to list of sessions that are subscribed to that property
    private static final Map<String,List<Session>> playerSessions = new HashMap<>();
    private static Map<UUID, List<Session>> gameSessions = new HashMap<>();

    private Application application = Application.getInstance();
    
    @OnOpen
    public void onConnect(Session session) {
        System.out.println("[WebSocket Connected] SessionID: " + session.getId());
        String message = String.format("[New client with client side session ID]: %s", session.getId());
        sessions.add(session);
        System.out.println("[#sessions]: " + sessions.size());
    }
    
    @OnMessage
    public void onText(String message, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Received] : " + message);

        handleMessageFromClient(message, session);
    }
    
    @OnClose
    public void onClose(CloseReason reason, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + " [Socket Closed]: " + reason);
        sessions.remove(session);
    }
    
    @OnError
    public void onError(Throwable cause, Session session) {
        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
        cause.printStackTrace(System.err);
    }

    // Handle incoming message from client
    private void handleMessageFromClient(String jsonMessage, Session session) {
        Gson gson = new Gson();
        CommunicatorWebSocketMessage wbMessage = null;
        RegisterPlayer registerPlayer = null;
        try {
            wbMessage = gson.fromJson(jsonMessage,CommunicatorWebSocketMessage.class);
            registerPlayer = gson.fromJson(wbMessage.getContent(), RegisterPlayer.class);
        }
        catch (JsonSyntaxException ex) {
            System.out.println("[WebSocket ERROR: cannot parse Json message " + jsonMessage);
            return;
        }
       
        // Operation defined in message
        CommunicatorWebSocketMessageOperation operation;
        operation = wbMessage.getOperation();
        
        // Process message based on operation
            switch (operation) {
                case REGISTERPLAYER:
                    // Register player if not registered yet
                    Game game;
                    if (registerPlayer.isSingeplayer()){
                        game = new Game();
                    }
                    else {
                        game = application.getOpenGame();
                    }
                    if(!gameSessions.containsKey(game.getId())) {
                        gameSessions.put(game.getId(), new ArrayList<Session>());
                    }
                    gameSessions.get(game.getId()).add(session);

                    int playerNr = game.setPlayer(registerPlayer.getName(), registerPlayer.getPassword(), registerPlayer.isSingeplayer());

                    CommunicatorWebSocketMessage message = new CommunicatorWebSocketMessage();
                    message.setOperation(CommunicatorWebSocketMessageOperation.REGISTERPLAYERRESPONS);
                    message.setGame(game.getId());
                    RegisterPlayerRespons registerPlayerRespons = new RegisterPlayerRespons(playerNr, registerPlayer.getName());
                    String content = gson.toJson(registerPlayerRespons);
                    message.setContent(content);
                    String convertedMessage = gson.toJson(message);
                    session.getAsyncRemote().sendText(convertedMessage);
                    break;
                case UNREGISTERPLAYER:
                    // Do nothing as player may also have been registered by
                    // another client
                    break;
//                case SUBSCRIBETOPROPERTY:
//                    // Subsribe to property if the property has been registered
//                    if (playerSessions.get(player) != null) {
//                        playerSessions.get(player).add(session);
//                    }
//                    break;
//                case UNSUBSCRIBEFROMPROPERTY:
//                    // Unsubsribe from property if the property has been registered
//                    if (playerSessions.get(player) != null) {
//                        playerSessions.get(player).remove(session);
//                    }
//                    break;
//                case UPDATEPROPERTY:
//                    // Send the message to all clients that are subscribed to this property
//                    if (playerSessions.get(player) != null) {
//                        System.out.println("[WebSocket send ] " + jsonMessage + " to:");
//                        for (Session sess : playerSessions.get(player)) {
//                            // Use asynchronous communication
//                            System.out.println("\t\t >> Client associated with server side session ID: " + sess.getId());
//                            sess.getAsyncRemote().sendText(jsonMessage);
//                        }
//                        System.out.println("[WebSocket end sending message to subscribers]");
//                    }
//                    break;
//                default:
//                    System.out.println("[WebSocket ERROR: cannot process Json message " + jsonMessage);
//                    break;
            }

    }
}
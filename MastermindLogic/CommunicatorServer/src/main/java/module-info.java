module CommunicatorServer {
    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires javax.websocket.api;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires gson;
    requires mastermindlogicserver;
    requires javax.websocket.client.api;
    exports communicatorserver;
}
package nl.fhict.s3.mastermindlogic;

import javax.websocket.server.ServerContainer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class MastermindLogicApplication {
	private static final int PORT = 8080;

	public static void main(String[] args) {
		Server wsServer = new Server();
        ServerConnector connector = new ServerConnector(wsServer);

        connector.setPort(PORT);
        wsServer.addConnector(connector);

        ServletContextHandler wsContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        wsContext.setContextPath("/");
        wsServer.setHandler(wsContext);

        try {
            ServerContainer wsContainer = WebSocketServerContainerInitializer.configureContext(wsContext);

            wsContainer.addEndpoint(GameEndpoint.class);
            wsServer.start();
            wsServer.join();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

	}
}


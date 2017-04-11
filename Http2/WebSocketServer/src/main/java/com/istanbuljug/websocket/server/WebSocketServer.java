package com.istanbuljug.websocket.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8444);

        WebSocketHandler wsHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory webSocketServletFactory) {
                webSocketServletFactory.register(ToUpperSocket.class);
            }
        };

        server.setHandler(wsHandler);

        server.start();
        server.join();
    }
}

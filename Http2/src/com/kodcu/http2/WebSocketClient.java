package com.kodcu.http2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class WebSocketClient {
    public static void main(String[] args) {
        URI uri = URI.create("ws://31.210.55.49:8444");

        Listener listener = new WebSocket.Listener() {
            @Override
            public void onOpen(WebSocket webSocket) {
                Listener.super.onOpen(webSocket);
                
                System.out.println("websocket opened");
            }

            @Override
            public void onClose(WebSocket webSocket, Optional<WebSocket.CloseCode> code, String reason) {
                Listener.super.onClose(webSocket, code, reason);
                System.out.println("websocket closed");
                
                System.exit(0);
            }

            @Override
            public CompletionStage<?> onText(WebSocket webSocket, CharSequence message, WebSocket.MessagePart part) {
                
                 System.out.println(message.toString());
                
                return Listener.super.onText(webSocket, message, part);
            }
            
        };

        WebSocket.Builder builder = WebSocket.newBuilder(uri, HttpClient.getDefault(), listener);
        
        builder.buildAsync().join().sendText("talip");
    }
}
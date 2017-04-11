package com.istanbuljug.http2;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.WebSocket;

import java.net.URI;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static jdk.incubator.http.WebSocket.*;

/**
 * Created by taner on 11.04.2017.
 */
public class WebSocketClient
{
	public static void main(String[] args) throws ExecutionException, InterruptedException
	{
		Listener listener = new Listener() {
			@Override
			public void onOpen(WebSocket webSocket) {
				Listener.super.onOpen(webSocket);

				System.out.println("websocket opened");
			}

			@Override
			public CompletionStage<?> onText(WebSocket webSocket, CharSequence message, MessagePart part) {

				System.out.println(message.toString());

				return Listener.super.onText(webSocket, message, part);
			}

		};

		HttpClient client = HttpClient.newBuilder().build();
		Builder builder = client.newWebSocketBuilder(URI.create("ws://localhost:8444"), listener);
		builder.buildAsync().join().sendText("Hello");

		Thread.sleep(60000);
	}
}

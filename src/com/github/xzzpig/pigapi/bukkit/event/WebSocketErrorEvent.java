package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketErrorEvent extends Event {
	private WebSocketServer server;
	private WebSocket client;
	private Exception error;

	public WebSocketErrorEvent(WebSocketServer server, WebSocket client, Exception error) {
		this.server = server;
		this.client = client;
		this.error = error;
	}

	public WebSocketServer getServer() {
		return server;
	}

	public WebSocket getClient() {
		return client;
	}

	public Exception getError() {
		return error;
	}
}

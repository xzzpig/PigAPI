package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketErrorEvent extends Event {
	private WebSocket client;
	private Exception error;
	private WebSocketServer server;

	public WebSocketErrorEvent(WebSocketServer server, WebSocket client, Exception error) {
		this.server = server;
		this.client = client;
		this.error = error;
	}

	public WebSocket getClient() {
		return client;
	}

	public Exception getError() {
		return error;
	}

	public WebSocketServer getServer() {
		return server;
	}
}

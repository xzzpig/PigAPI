package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketMessageEvent extends Event {
	private WebSocketServer server;
	private WebSocket client;
	private String message;

	public WebSocketMessageEvent(WebSocketServer server, WebSocket client, String message) {
		this.server = server;
		this.client = client;
		this.message = message;
	}

	public WebSocketServer getServer() {
		return server;
	}

	public WebSocket getClient() {
		return client;
	}

	public String getMessage() {
		return message;
	}
}

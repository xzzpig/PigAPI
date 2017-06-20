package com.github.xzzpig.pigapi.bukkit.event;

import com.github.xzzpig.pigutils.event.Event;
import com.github.xzzpig.pigutils.websocket.WebSocket;
import com.github.xzzpig.pigutils.websocket.server.WebSocketServer;

public class WebSocketMessageEvent extends Event {
	private WebSocket client;
	private String message;
	private WebSocketServer server;

	public WebSocketMessageEvent(WebSocketServer server, WebSocket client, String message) {
		this.server = server;
		this.client = client;
		this.message = message;
	}

	public WebSocket getClient() {
		return client;
	}

	public String getMessage() {
		return message;
	}

	public WebSocketServer getServer() {
		return server;
	}
}

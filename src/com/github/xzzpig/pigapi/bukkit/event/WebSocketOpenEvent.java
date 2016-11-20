package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketOpenEvent extends Event {
	private WebSocketServer server;
	private WebSocket client;
	private ClientHandshake handshake;

	public WebSocketOpenEvent(WebSocketServer server, WebSocket client, ClientHandshake handshake) {
		this.server = server;
		this.client = client;
		this.handshake = handshake;
	}

	public WebSocketServer getServer() {
		return server;
	}

	public WebSocket getClient() {
		return client;
	}

	public ClientHandshake getHandshake() {
		return handshake;
	}
}

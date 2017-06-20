package com.github.xzzpig.pigapi.bukkit.event;

import com.github.xzzpig.pigutils.event.Event;
import com.github.xzzpig.pigutils.websocket.WebSocket;
import com.github.xzzpig.pigutils.websocket.handshake.ClientHandshake;
import com.github.xzzpig.pigutils.websocket.server.WebSocketServer;

public class WebSocketOpenEvent extends Event {
	private WebSocket client;
	private ClientHandshake handshake;
	private WebSocketServer server;

	public WebSocketOpenEvent(WebSocketServer server, WebSocket client, ClientHandshake handshake) {
		this.server = server;
		this.client = client;
		this.handshake = handshake;
	}

	public WebSocket getClient() {
		return client;
	}

	public ClientHandshake getHandshake() {
		return handshake;
	}

	public WebSocketServer getServer() {
		return server;
	}
}

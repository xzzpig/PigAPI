package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketCloseEvent extends Event {
	private WebSocket client;
	private int code;
	private String reason;
	private boolean remote;
	private WebSocketServer server;

	public WebSocketCloseEvent(WebSocketServer server, WebSocket client, int code, String reason, boolean remote) {
		this.server = server;
		this.client = client;
	}

	public WebSocket getClient() {
		return client;
	}

	public int getCode() {
		return code;
	}

	public String getReason() {
		return reason;
	}

	public WebSocketServer getServer() {
		return server;
	}

	public boolean isRemote() {
		return remote;
	}
}

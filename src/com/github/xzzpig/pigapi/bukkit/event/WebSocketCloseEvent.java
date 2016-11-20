package com.github.xzzpig.pigapi.bukkit.event;

import org.java_websocket.WebSocket;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.event.Event;

public class WebSocketCloseEvent extends Event {
	private WebSocketServer server;
	private WebSocket client;
	private int code;
	private String reason;
	private boolean remote;
	public WebSocketCloseEvent(WebSocketServer server, WebSocket client, int code, String reason, boolean remote) {
		this.server = server;
		this.client = client;
	}

	public WebSocketServer getServer() {
		return server;
	}

	public WebSocket getClient() {
		return client;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getReason(){
		return reason;
	}
	
	public boolean isRemote(){
		return remote;
	}
}

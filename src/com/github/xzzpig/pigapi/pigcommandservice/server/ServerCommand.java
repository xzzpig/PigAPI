package com.github.xzzpig.pigapi.pigcommandservice.server;

import org.java_websocket.WebSocket;

import com.github.xzzpig.pigapi.json.JSONObject;
import com.github.xzzpig.pigapi.pigcommandservice.Command;

public abstract class ServerCommand extends Command {
	public WebSocket getClient(JSONObject msg) {
		return (WebSocket) msg.opt("client");
	}

	public CommandServer getServer() {
		return CommandServer.getServerInstance();
	}
}

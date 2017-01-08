package com.github.xzzpig.pigapi.tcp;

import com.github.xzzpig.pigapi.event.Event;

public class ClientConnectEvent extends Event {
	private Client client;
	private Server server;

	public ClientConnectEvent(Server server, Client client) {
		this.client = client;
		this.server = server;
	}

	public Client getClient() {
		return client;
	}

	public Server getServer() {
		return server;
	}
}

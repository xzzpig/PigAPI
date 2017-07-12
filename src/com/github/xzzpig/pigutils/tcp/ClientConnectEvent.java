package com.github.xzzpig.pigutils.tcp;

import com.github.xzzpig.pigutils.event.Event;

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

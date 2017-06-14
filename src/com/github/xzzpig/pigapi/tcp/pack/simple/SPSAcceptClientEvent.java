package com.github.xzzpig.pigapi.tcp.pack.simple;

import com.github.xzzpig.pigapi.event.Event;

public class SPSAcceptClientEvent extends Event {
	SimplePackageClient c;
	SimplePackageServer s;

	SPSAcceptClientEvent(SimplePackageServer server, SimplePackageClient client) {
		c = client;
		s = server;
	}

	public SimplePackageClient getClient() {
		return c;
	}

	public SimplePackageServer getServer() {
		return s;
	}
}

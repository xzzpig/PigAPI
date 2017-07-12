package com.github.xzzpig.pigutils.tcp.pack.simple;

import com.github.xzzpig.pigutils.event.Event;

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

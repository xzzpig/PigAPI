package com.github.xzzpig.pigutils.tcp.pack.simple;

import com.github.xzzpig.pigutils.event.Event;

public class SPCReceivePackageEvent extends Event {
	private SimplePackage package1;
	private SimplePackageClient client;

	public SPCReceivePackageEvent(SimplePackageClient c, SimplePackage p) {
		client = c;
		package1 = p;
	}

	public SimplePackageClient getClient() {
		return client;
	}

	public SimplePackage getPackage() {
		return package1;
	}
}

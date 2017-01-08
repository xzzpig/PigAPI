package com.github.xzzpig.pigapi.tcp.pack;

import java.net.ServerSocket;

public interface PackageServer {
	public PackageClient acceptClient();

	public PackageClient[] getPackageClients();

	public ServerSocket getServer();
}

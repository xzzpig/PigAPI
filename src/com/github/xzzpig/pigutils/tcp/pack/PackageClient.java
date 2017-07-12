package com.github.xzzpig.pigutils.tcp.pack;

import java.net.Socket;

public interface PackageClient {
	public Socket getSocket();

	public Package receivePackage();

	public boolean sendPackage(Package pack);
}

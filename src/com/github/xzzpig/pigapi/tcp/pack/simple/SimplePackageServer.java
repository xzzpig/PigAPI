package com.github.xzzpig.pigapi.tcp.pack.simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.tcp.pack.PackageClient;
import com.github.xzzpig.pigapi.tcp.pack.PackageServer;

public class SimplePackageServer implements PackageServer {
	private java.util.List<PackageClient> clients;

	private ServerSocket serverSocket;

	private Thread thread;

	public SimplePackageServer(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		clients = new ArrayList<>();
	}

	@Override
	public ServerSocket getServer() {
		return serverSocket;
	}

	@Override
	public PackageClient[] getPackageClients() {
		return clients.toArray(new PackageClient[0]);
	}

	@Override
	public SimplePackageClient acceptClient() {
		try {
			SimplePackageClient client = new SimplePackageClient(serverSocket.accept());
			clients.add(client);
			return client;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void startAutoAcceptThread() {
		thread = new Thread(() -> {
			while (!serverSocket.isClosed()) {
				SimplePackageClient c = acceptClient();
				Event.callEvent(new SPSAcceptClientEvent(this, c));
			}
		});
		thread.start();
	}

	@SuppressWarnings("deprecation")
	public boolean stopAutoAcceptThread() {
		if (thread != null) {
			try {
				thread.stop();
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if (thread != null) {
			try {
				thread.stop();
			} catch (Exception e) {
			}
		}
	}

}

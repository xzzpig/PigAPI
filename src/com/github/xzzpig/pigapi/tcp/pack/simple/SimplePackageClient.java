package com.github.xzzpig.pigapi.tcp.pack.simple;

import java.io.IOException;
import java.net.Socket;

import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.tcp.pack.Package;
import com.github.xzzpig.pigapi.tcp.pack.PackageClient;

public class SimplePackageClient implements PackageClient {
	private Socket socket;
	private Thread thread;

	public SimplePackageClient(Socket socket) {
		this.socket = socket;
	}

	@Override
	public Socket getSocket() {
		return socket;
	}

	@Override
	public SimplePackage receivePackage() {
		try {
			int len = socket.getInputStream().read();
			byte[] data = new byte[len];
			socket.getInputStream().read(data);
			return new SimplePackage(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean sendPackage(Package pack) {
		try {
			socket.getOutputStream().write(pack.size());
			socket.getOutputStream().write(pack.getData());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void startAutoReveivePackageThread() {
		thread = new Thread(() -> {
			while (!socket.isClosed()) {
				SimplePackage p = receivePackage();
				Event.callEvent(new SPCReceivePackageEvent(this, p));
			}
		});
		thread.start();
	}

}

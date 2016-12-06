package com.github.xzzpig.pigapi.tcp;

import java.net.Socket;

public abstract class AcceptData extends Thread {
	public AcceptData() {
	}

	public abstract Client getClient();

	public abstract Socket getSocket();

	@Override
	public abstract void run();

	public abstract AcceptData setClient(Client c);

	public abstract AcceptData setSocket(Socket s);
}

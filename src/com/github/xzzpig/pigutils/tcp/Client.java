package com.github.xzzpig.pigutils.tcp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.github.xzzpig.pigutils.Debuger;
import com.github.xzzpig.pigutils.PigData;
import com.github.xzzpig.pigutils.TData;

public class Client {
	public static Class<? extends AcceptData> acceptDataClass = AcceptData_Default.class;
	public static Client client;

	public static List<Client> clients = new ArrayList<Client>();

	public long cooldown = System.currentTimeMillis();
	public TData data = new TData();
	public String from;
	public Socket s;

	public Client(Socket s) {
		clients.add(this);
		this.s = s;
		this.from = "server";
		acceptData();
	}

	public Client(String ip, int port) throws Exception {
		InetAddress add = InetAddress.getByName(ip);
		this.s = new Socket(add, port);
		this.from = "client";
		client = this;
	}

	private void acceptData() {
		try {
			AcceptData ad = acceptDataClass.newInstance();
			ad.setClient(this);
			ad.start();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public byte[] receiveData(int timeout) {
		try {
			s.setSoTimeout(timeout);
			byte[] data = new byte[1024 * 1024];
			int length = s.getInputStream().read(data);
			String str = new String(data, 0, length);

			s.setSoTimeout(0);
			return str.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object receiveObjectData(int timeout) {
		ObjectInputStream in = null;
		try {
			s.setSoTimeout(timeout);
			in = new ObjectInputStream(s.getInputStream());
			Object object = in.readObject();
			s.setSoTimeout(0);
			return object;
		} catch (Exception e) {
			try {
				s.setSoTimeout(0);
			} catch (SocketException e1) {
				Debuger.print(e);
			}
			System.out.println("数据接受错误");
		}
		return null;
	}

	public Client sendData(byte[] data) {
		try {
			OutputStream out = s.getOutputStream();
			out.write(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据发送错误");
			if (s.isClosed() && from.equalsIgnoreCase("server"))
				Server.server.getClients().remove(this);
		}
		return this;
	}

	public Client sendData(Object data) {
		while (System.currentTimeMillis() < cooldown) {
		}
		try {
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(data);
			cooldown = System.currentTimeMillis() + 50;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据发送错误");
		}
		return this;
	}

	public Client sendData(PigData data) {
		sendData(data.toString().getBytes());
		return this;
	}
}

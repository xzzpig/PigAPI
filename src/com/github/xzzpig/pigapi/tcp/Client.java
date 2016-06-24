package com.github.xzzpig.pigapi.tcp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.github.xzzpig.pigapi.Debuger;
import com.github.xzzpig.pigapi.PigData;
import com.github.xzzpig.pigapi.TData;

public class Client {
	public static List<Client> clients = new ArrayList<Client>();
	public static Client client;
	
	public static Class<? extends AcceptData> acceptDataClass = AcceptData_Default.class;

	public Socket s;
	public String from;
	public TData data = new TData();

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

	public Client sendData(Object data) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(data);
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
	
	public Client sendData(byte[] data) {
		try {
			OutputStream out = s.getOutputStream();
			out.write(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据发送错误");
		}
		return this;
	}

	public Object receiveData(int timeout) {
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

	private void acceptData() {
		try {
			AcceptData ad = acceptDataClass.newInstance();
			ad.start();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}

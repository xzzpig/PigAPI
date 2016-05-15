package com.github.xzzpig.pigapi.tcp;
import java.net.*;
import java.util.*;
import java.io.*;

import com.github.xzzpig.pigapi.TData;
import com.github.xzzpig.pigapi.event.*;
import com.github.xzzpig.pigapi.customevent.*;

public class Client
{
	public static List<Client> clients = new ArrayList<Client>();
	public static Client client;
	
	public Socket s;
	public String from;
	public TData data = new TData();

	public Client(Socket s){
		clients.add(this);
		this.s = s;
		this.from = "server";
		acceptData();
	}
	public Client(String ip,int port) throws Exception{
		InetAddress add = InetAddress.getByName(ip);
		this.s = new Socket(add,port);
		this.from = "client";
		client = this;
	}
	
	public Client sendData(Object data){
		try{
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(data);
		}
		catch(Exception e){e.printStackTrace();System.out.println("数据发送错误");}
		return this;
	}
	public Object receiveData(int timeout){
					ObjectInputStream in = null;
					try{
						s.setSoTimeout(timeout);
						in = new ObjectInputStream(s.getInputStream());
					Object object = in.readObject();
					s.setSoTimeout(0);
					return object;
					}
					catch(Exception e){try {
						s.setSoTimeout(0);
					} catch (SocketException e1) {
					}System.out.println("数据接受错误");}
					return null;
			
	}
	private void acceptData(){
		new Thread(new Runnable(){
				@Override
				public void run(){
					ObjectInputStream in = null;
					try{
						in = new ObjectInputStream(s.getInputStream());
					}
					catch(Exception e){System.out.println("数据接受错误");}
					while(in!=null||s.isConnected()){
						try{
							Object data = in.readObject();
							Event.callEvent(new ServerDataReachEvent(Client.this,data));
						}
						catch(Exception e){}
					}
				}
			}).start();
	}
}

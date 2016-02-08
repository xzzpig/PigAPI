package com.github.xzzpig.pigapi.tcp;
import java.net.ServerSocket;

public class Server
{
	public static Server server;
	
	public ServerSocket ss;
	
	public Server(int port) throws Exception{
		Server.server = this;
		this.ss = new ServerSocket(port);
		new Thread(new Runnable(){
				@Override
				public void run(){
					while(true){
						try{
							new Client(ss.accept());
							System.out.println("新客户端连接");
						}
						catch(Exception e){
							System.out.println("服务器接受客户端错误");
						}
					}
				}
		}).start();
	}
}

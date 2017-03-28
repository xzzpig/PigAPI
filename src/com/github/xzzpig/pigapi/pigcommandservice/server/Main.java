package com.github.xzzpig.pigapi.pigcommandservice.server;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		CommandServer.getServerInstance().open(10727).start();
		Scanner s = new Scanner(System.in);
		String str;
		System.out.print(">");
		while (!(str = s.nextLine()).equalsIgnoreCase("stop")) {
			CommandServer.getServerInstance().runCommand(str);
			System.out.print(">");
		}
		s.close();
		CommandServer.getServerInstance().stop();
		Logger.getAnonymousLogger().info("CommandServer已关闭");
	}

}

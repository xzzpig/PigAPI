package com.github.xzzpig.pigapi.pigcommandservice.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		CommandClient client = new CommandClient().open("localhost", 10727).connect();
		Scanner s = new Scanner(System.in);
		String str;
		System.out.print(">");
		while (!(str = s.nextLine()).equalsIgnoreCase("stop")) {
			client.runCommand(str);
			System.out.print(">");
		}
		s.close();
		client.stop();
		Logger.getAnonymousLogger().info("CommandClient已斷開");
	}
}

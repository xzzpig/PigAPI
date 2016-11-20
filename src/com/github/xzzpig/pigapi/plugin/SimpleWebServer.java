package com.github.xzzpig.pigapi.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.NanoHTTPD;
import org.nanohttpd.protocols.http.response.Response;

import com.github.xzzpig.pigapi.bukkit.event.SimpleWebServeEvent;
import com.github.xzzpig.pigapi.event.Event;

public class SimpleWebServer extends NanoHTTPD {

	public SimpleWebServer(int port) {
		super(port);
	}

	@Override
	public Response serve(IHTTPSession session) {
		String uri = session.getUri();
		if (uri.endsWith("/")) {
			uri += "index.html";
		}
		File folder = new File(Main.self.getDataFolder(), "/web");
		if ((!folder.exists()) || folder.isFile()) {
			folder.delete();
			folder.mkdirs();
		}
		File file = new File(folder, uri);
		Scanner scanner;
		String str = "";
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				str += scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			str = "Page Not Found";
		}
		Response response = Response.newFixedLengthResponse(str);
		SimpleWebServeEvent event = new SimpleWebServeEvent(session, response);
		Event.callEvent(event);
		return event.getResponse();
	}
}

package com.github.xzzpig.pigapi.pigsimpleweb.event;

import java.io.File;
import java.io.IOException;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.request.Method;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;

import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.event.EventHandler;
import com.github.xzzpig.pigapi.event.EventHandler.EventRunLevel;
import com.github.xzzpig.pigapi.event.Listener;
import com.github.xzzpig.pigapi.pigsimpleweb.MIME;

public class PigSWSListener implements Listener{	
	@EventHandler(mainLevel=EventRunLevel.Highest)
	public void onPigSWSServerEvent_get_default(PigSWSServeEvent event){
		IHTTPSession session = event.getSession();
		if (session.getMethod()!=Method.GET) {
			return;
		}
		String uri = session.getUri();
		if (uri.endsWith("/")) {
			PigSWSUriIsDicEvent pigSWSUriIsDicEvent = new PigSWSUriIsDicEvent(event.getPigSimpleWebServer(),uri);
			Event.callEvent(pigSWSUriIsDicEvent);
			uri+=pigSWSUriIsDicEvent.getFileName();
			session.setUri(uri);
		}
		PigSWSSolveMIMEEvent pigSWSSolveMIMEEvent = new PigSWSSolveMIMEEvent(event.getPigSimpleWebServer(), session,event.getPigSimpleWebServer().getMIMEby(uri));
		Event.callEvent(pigSWSSolveMIMEEvent);
		event.setResponse(pigSWSSolveMIMEEvent.getResponse());
	}
	
	@EventHandler(mainLevel=EventRunLevel.Highest)
	public void onPigSWSUriIsDicEvent(PigSWSUriIsDicEvent event){
		event.setFileName("index.html");
	}
	
	@EventHandler()
	public void onPigSWSSolveMIMEEvent_text_default(PigSWSSolveMIMEEvent event){
		if (event.getMIME().getSolveTyle().equalsIgnoreCase("text")||event.getMIME().getSolveTyle().equalsIgnoreCase("file")) {
			File file = new File(event.getPigSimpleWebServer().getRootDir()+event.getSession().getUri());
			if(!file.exists()){
				PigSWSFileNotFoundEvent pigSWSFileNotFoundEvent = new PigSWSFileNotFoundEvent(event.getPigSimpleWebServer(), event.getSession());
				event.setResponse(pigSWSFileNotFoundEvent.getResponse());
				return;
			}
			try {
				Response response = Response.newFixedLengthResponse(Status.OK, event.getMIME().getName(),file.toURI().toURL().openStream(),file.length());
				event.setResponse(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void onPigSWSGetMIMEEVent(PigSWSGetMIMEEvent event){
		switch (event.getType()) {
		case "html":
		case "htm":
			event.setMIME(MIME.text_html);
			break;
		}
	}
}

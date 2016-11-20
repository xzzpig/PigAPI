package com.github.xzzpig.pigapi.bukkit.event;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;

import com.github.xzzpig.pigapi.event.Event;

public class SimpleWebServeEvent extends Event {
	private IHTTPSession session;
	private Response response;

	public SimpleWebServeEvent(IHTTPSession session, Response response) {
		this.session = session;
		this.response = response;
	}

	public IHTTPSession getSession() {
		return session;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		if (response != null)
			this.response = response;
	}

}

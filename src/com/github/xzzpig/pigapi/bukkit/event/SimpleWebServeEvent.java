package com.github.xzzpig.pigapi.bukkit.event;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;

public class SimpleWebServeEvent extends com.github.xzzpig.pigutils.event.Event {
	private Response response;
	private IHTTPSession session;

	public SimpleWebServeEvent(IHTTPSession session, Response response) {
		this.session = session;
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

	public IHTTPSession getSession() {
		return session;
	}

	public void setResponse(Response response) {
		if (response != null)
			this.response = response;
	}

}

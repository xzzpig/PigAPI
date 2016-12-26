package com.github.xzzpig.pigapi.pigsimpleweb.event;

import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;

import com.github.xzzpig.pigapi.pigsimpleweb.MIME;
import com.github.xzzpig.pigapi.pigsimpleweb.PigSimpleWebServer;

public class PigSWSSolveMIMEEvent extends PigSWSEvent{

	private IHTTPSession session;
	private Response response;
	private MIME mime;
	public PigSWSSolveMIMEEvent(PigSimpleWebServer psws,IHTTPSession session,MIME mime) {
		super(psws);
		this.session = session;
		this.mime = mime;
	}
	
	public IHTTPSession getSession(){
		return session;
	}
	
	public void setResponse(Response r){
		response =r;
	}
	
	Response getResponse(){
		return response;
	}
	
	public MIME getMIME(){
		return mime;
	}

}

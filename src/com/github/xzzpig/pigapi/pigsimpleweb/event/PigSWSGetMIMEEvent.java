package com.github.xzzpig.pigapi.pigsimpleweb.event;

import com.github.xzzpig.pigapi.pigsimpleweb.MIME;
import com.github.xzzpig.pigapi.pigsimpleweb.PigSimpleWebServer;

/*
 * 获取某指定类型的MIME
 */
public class PigSWSGetMIMEEvent extends PigSWSEvent{

	String t;
	
	MIME m;
	
	public PigSWSGetMIMEEvent(PigSimpleWebServer psws,String type) {
		super(psws);
		t = type;
	}
	
	public String getType(){
		return t;
	}
	
	public void setMIME(MIME mime ){
		m = mime;
	}
	
	public MIME getMIME(){
		return m;
	}
}

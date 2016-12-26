package com.github.xzzpig.pigapi.pigsimpleweb.event;

import com.github.xzzpig.pigapi.pigsimpleweb.PigSimpleWebServer;

/*
 * 请求的URI以/结尾时触发
 * 用于指定默认文件名
 */
public class PigSWSUriIsDicEvent extends PigSWSEvent {

	private String fileName;
	private String uri;
	
	public PigSWSUriIsDicEvent(PigSimpleWebServer psws,String uri) {
		super(psws);
		this.uri = uri;
		// TODO Auto-generated constructor stub
	}

	/*
	 * 设置默认文件名称
	 */
	public void setFileName(String f){
		fileName = f;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public String getURI(){
		return uri;
	}
}

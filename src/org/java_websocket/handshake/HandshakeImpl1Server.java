package org.java_websocket.handshake;

public class HandshakeImpl1Server extends HandshakedataImpl1 implements ServerHandshakeBuilder {
	private short httpstatus;
	private String httpstatusmessage;

	public HandshakeImpl1Server() {
	}

	@Override
	public String getHttpStatusMessage() {
		return httpstatusmessage;
	}

	@Override
	public short getHttpStatus() {
		return httpstatus;
	}

	@Override
	public void setHttpStatusMessage(String message) {
		this.httpstatusmessage = message;
	}

	@Override
	public void setHttpStatus(short status) {
		httpstatus = status;
	}

}

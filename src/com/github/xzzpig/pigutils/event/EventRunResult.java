package com.github.xzzpig.pigutils.event;

public class EventRunResult {
	private String msg;

	public EventRunResult(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "EventResult:" + msg;
	}
}

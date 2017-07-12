package com.github.xzzpig.pigutils.tcp;

import com.github.xzzpig.pigutils.PigData;
import com.github.xzzpig.pigutils.event.Event;

public class ServerDataReachEvent extends Event {
	private Client client;
	private Object data;

	public ServerDataReachEvent(Client client, Object data) {
		this.client = client;
		this.data = data;
	}

	public Client getClient() {
		return client;
	}

	public Object getData() {
		return data;
	}

	public PigData getPigData() {
		return new PigData(data.toString());
	}
}

package com.github.xzzpig.pigapi.bukkit.event;

import java.util.HashMap;

import com.github.xzzpig.pigapi.event.Event;

public class StringMatcherEvent extends Event {
	HashMap<String,Object> data;
	String souce;

	public StringMatcherEvent(String souce, HashMap<String,Object> data) {
		this.souce = souce;
		this.data = data;
	}

	public HashMap<String,Object> getData() {
		return data;
	}

	public String getSouce() {
		return souce;
	}

	public void setData(HashMap<String,Object> data) {
		this.data = data;
	}

	public void setSouce(String souce) {
		this.souce = souce;
	}
}

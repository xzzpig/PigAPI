package com.github.xzzpig.pigapi.event;

public interface Listener {
	public default String getName() {
		return this.toString();
	}
}

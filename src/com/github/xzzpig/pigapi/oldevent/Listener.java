package com.github.xzzpig.pigapi.oldevent;

public interface Listener {
	public default String getName() {
		return this.toString();
	}
}

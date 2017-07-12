package com.github.xzzpig.pigutils.oldevent;

public interface Listener {
	public default String getName() {
		return this.toString();
	}
}

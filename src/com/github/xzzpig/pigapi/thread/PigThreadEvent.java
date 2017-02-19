package com.github.xzzpig.pigapi.thread;

import com.github.xzzpig.pigapi.event.Event;

public abstract class PigThreadEvent extends Event {
	private PigThread t;

	public PigThreadEvent(PigThread t) {
		this.t = t;
	}

	public PigThread getPigThread() {
		return t;
	}
}

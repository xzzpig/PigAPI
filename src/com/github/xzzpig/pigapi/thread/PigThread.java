package com.github.xzzpig.pigapi.thread;

import com.github.xzzpig.pigapi.event.EventAdapter;
import com.github.xzzpig.pigapi.event.EventBus;

public class PigThread extends Thread implements EventAdapter {
	public EventBus e = new EventBus();

	public PigThread(Runnable r) {
		super(r);
	}

	public PigThread(Thread t) {
		super(t::run);
	}

	@Override
	public void run() {
		this.callEvent(new PigThreadStartEvent(this));
		super.run();
		this.callEvent(new PigThreadFinishEvent(this));
	}

	@Override
	public EventBus getEventBus() {
		return e;
	}
}

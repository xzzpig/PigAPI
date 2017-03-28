package com.github.xzzpig.pigapi.thread;

import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.event.EventAdapter;
import com.github.xzzpig.pigapi.event.SimpleListener;

public class PigThread extends Thread implements EventAdapter {
	public Event e = new Event();

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
	public Event getEventInstance() {
		return e;
	}

	public <T extends Event> PigThread regListener(SimpleListener<T> l) {
		registListener(l);
		return this;
	}
}

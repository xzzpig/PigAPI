package com.github.xzzpig.pigutils.oldevent;

@FunctionalInterface
public interface SimpleListener<T extends Event> extends Listener {

	public default EventHandler getHandler() {
		return Event.defaultEventHandler;
	}

	@EventHandler
	public void run(T o);
}

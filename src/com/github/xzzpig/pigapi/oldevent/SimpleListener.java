package com.github.xzzpig.pigapi.oldevent;

@FunctionalInterface
public interface SimpleListener<T extends Event> extends Listener {

	@EventHandler
	public void run(T o);

	public default EventHandler getHandler() {
		return Event.defaultEventHandler;
	}
}

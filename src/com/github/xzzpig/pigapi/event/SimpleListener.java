package com.github.xzzpig.pigapi.event;

@FunctionalInterface
public interface SimpleListener extends Listener {

	@EventHandler
	public void run(Event o);
}

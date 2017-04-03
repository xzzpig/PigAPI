package com.github.xzzpig.pigapi.event;

import java.util.function.Predicate;

/**
 * 持有一个EventBus对象 可作为事件的接收体
 * 
 * @author xzzpig
 *
 */
public interface EventAdapter {
	/**
	 * @return 持有的EventBus,作为该类其他方法的基础EventBus
	 */
	public EventBus getEventBus();

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#callEvent(Event)}
	 */
	public default EventRunResult callEvent(Event e) {
		return getEventBus().callEvent(e);
	}

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#callEvent(Event, EventTunnel)}
	 */
	public default EventRunResult callEvent(Event e, EventTunnel tunnel) {
		return getEventBus().callEvent(e, tunnel);
	}

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#regRunner(EventRunner)}
	 */
	public default EventAdapter regRunner(EventRunner<Event> runner) {
		getEventBus().regRunner(runner);
		return this;
	}

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#regListener(Listener)}
	 */
	public default EventAdapter regListener(Listener listener) {
		getEventBus().regListener(listener);
		return this;
	}

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#unregRunner(Predicate)}
	 */
	public default EventAdapter unregRunner(Predicate<EventRunner<?>> p) {
		getEventBus().unregRunner(p);
		return this;
	}

	/**
	 * 调用{@link EventAdapter#getEventBus()}的{@link EventBus#unregListener(Listener)}
	 */
	public default EventAdapter unregListener(Listener listener) {
		getEventBus().unregListener(listener);
		return this;
	}
}

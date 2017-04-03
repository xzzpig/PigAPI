package com.github.xzzpig.pigapi.event;

import java.util.function.Predicate;

/**
 * 事件Event由EventBus控制按一定顺序执行
 * 
 * @author xzzpig
 *
 */
public class Event {

	private static final EventBus eventInstance = new EventBus();

	/**
	 * 调用默认的EventBus的{@link EventBus#callEvent(Event)}
	 */
	public static final EventRunResult callEvent(Event event) {
		return eventInstance.callEvent(event);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regRunner(EventRunner)}
	 */
	public static final void regRunner(EventRunner<?> runner) {
		eventInstance.regRunner(runner);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#callEvent(Event, EventTunnel)}
	 */
	public static final EventRunResult callEvent(Event e, EventTunnel tunnel) {
		return eventInstance.callEvent(e, tunnel);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regListener(Listener)}
	 */
	public static final void registListener(Listener listener) {
		eventInstance.regListener(listener);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregListener(Listener)}
	 */
	public static final void unregListener(Listener listener) {
		eventInstance.unregListener(listener);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregRunner(Predicate)}
	 */
	public static final void unregRunner(Predicate<EventRunner<?>> p) {
		eventInstance.unregRunner(p);
	}

	/**
	 * 默认为this.getClass().getSimpleName()
	 * 
	 * @return 事件名称
	 */
	public String getName() {
		return this.getClass().getSimpleName();
	}

	private boolean cancel;

	/**
	 * 设置事件是否继续传递下去
	 * 
	 * @param cancel
	 *            true:继续,false:不继续
	 * @return this
	 */
	public Event setCanceled(boolean cancel) {
		this.cancel = cancel;
		return this;
	}

	/**
	 * @return 事件是否继续传递
	 */
	public boolean isCanceled() {
		return cancel;
	}
}

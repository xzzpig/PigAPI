package com.github.xzzpig.pigutils.event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * 事件Event由EventBus控制按一定顺序执行
 * 
 * @author xzzpig
 *
 */
/**
 * @author xzzpig
 *
 */
/**
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
	 * 调用默认的EventBus的{@link EventBus#callEvent(Event, EventTunnel)}
	 */
	public static final EventRunResult callEvent(Event e, EventTunnel tunnel) {
		return eventInstance.callEvent(e, tunnel);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regListener(Class)}
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static final void registListener(Class<Listener> c) throws InstantiationException, IllegalAccessException {
		eventInstance.regListener(c);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regListener(Listener)}
	 */
	public static final void registListener(Listener listener) {
		eventInstance.regListener(listener);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regRunner(Class)}
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static final void regRunner(Class<? extends EventRunner<?>> c)
			throws InstantiationException, IllegalAccessException {
		eventInstance.regRunner(c);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#regRunner(EventRunner)}
	 */
	public static final void regRunner(EventRunner<?> runner) {
		eventInstance.regRunner(runner);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregListener(Class)}
	 */
	public static final void unregListener(Class<Listener> c) {
		eventInstance.unregListener(c);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregListener(Listener)}
	 */
	public static final void unregListener(Listener listener) {
		eventInstance.unregListener(listener);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregRunner(Class)}
	 */
	public static final void unregRunner(Class<EventRunner<?>> c) {
		eventInstance.unregRunner(c);
	}

	/**
	 * 调用默认的EventBus的{@link EventBus#unregRunner(Predicate)}
	 */
	public static final void unregRunner(Predicate<EventRunner<?>> p) {
		eventInstance.unregRunner(p);
	}

	private boolean cancel;

	List<EventRunner<?>> runners;

	EventBus eventbus;

	Map<Object, Object> extras;

	/**
	 * @return 执行的 {@link EventBus}
	 */
	public final EventBus getEventBus() {
		return eventbus;
	}

	/**
	 * 获取 {@link EventRunner} 的执行列表 只有在
	 * {@link EventBus#callEvent(Event, EventTunnel)} 后才能获取非null
	 * 
	 * @return {@link EventRunner} 的执行列表
	 */
	public final List<EventRunner<?>> getEventRunnerList() {
		return runners;
	}

	public Map<Object, Object> getExtraMap() {
		return extras;
	}

	/**
	 * 获取额外的数据
	 * 
	 * @param key
	 * @param clazz
	 * @return 额外数据,如果key不存在则null
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtras(Object key, Class<T> clazz) {
		if (extras == null)
			return null;
		if (!extras.containsKey(key))
			return null;
		return (T) extras.get(key);
	}

	/**
	 * 默认为this.getClass().getSimpleName()
	 * 
	 * @return 事件名称
	 */
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return 事件是否继续传递
	 */
	public boolean isCanceled() {
		return cancel;
	}

	/**
	 * 存放额外的数据
	 * 
	 * @param key
	 * @param value
	 * @return this
	 */
	public Event putExtras(Object key, Object value) {
		if (extras == null)
			extras = new HashMap<>();
		extras.put(key, value);
		return this;
	}

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
}

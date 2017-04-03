package com.github.xzzpig.pigapi.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.github.xzzpig.pigapi.json.JSONObject;

/**
 * 作为EventRunner的载体
 * 
 * @author xzzpig
 *
 */
public class EventBus {

	private static final List<EventRunner<?>> runners = new ArrayList<>();

	/**
	 * callEvent(e,null)
	 * 
	 * @see EventBus#callEvent(Event, EventTunnel)
	 */
	public EventRunResult callEvent(Event e) {
		return callEvent(e, null);
	}

	/**
	 * 将e作为参数,按一定顺序调用this注册的EventRunner 当
	 * {@link EventRunner#getEventTunnel()}.equal(tunnel) 或 tunnel==null 且
	 * {@link EventRunner#getLimits()}的每个元素test下来都为true时 EventRunner才会被执行
	 * 
	 * @param e
	 *            事件
	 * @param tunnel
	 *            事件通道,null则为所有通道
	 * @return 最后一个调用的 {@link EventRunner#run(Event)} 的返回值
	 */
	public EventRunResult callEvent(Event e, EventTunnel tunnel) {
		EventRunResult result = null;
		run: for (EventRunner<?> r : runners) {
			if (e.isCanceled() && r.ignoreCanceled() == false)
				continue;
			if (tunnel != null && !r.getEventTunnel().equals(tunnel)) {
				continue;
			}
			if (r.getLimits() != null)
				for (Predicate<Event> p : r.getLimits())
					if (!p.test(e))
						continue run;
			@SuppressWarnings("unchecked")
			EventRunner<Event> r2 = (EventRunner<Event>) r;
			try {
				result = r2.run(e);
			} catch (ClassCastException e2) {
			}
		}
		return result;
	}

	/**
	 * 将listener中含@{@link EventHandler}注解的方法解析为EventRunner 并使用
	 * {@link EventBus#regRunner(EventRunner)}方法注册
	 * 
	 * @see EventBus#regRunner(EventRunner)
	 * @see EventHandler
	 * @param listener
	 * @return this
	 */
	public EventBus regListener(Listener listener) {
		for (Method method : listener.getClass().getDeclaredMethods()) {
			EventHandler handler = method.getDeclaredAnnotation(EventHandler.class);
			if (handler == null)
				continue;
			method.setAccessible(true);
			regRunner(new EventRunner<Event>() {

				public boolean canRun(Event e) {
					Class<?> target = (Class<?>) method.getGenericParameterTypes()[0];
					return target.isAssignableFrom(e.getClass());
				}

				public EventTunnel getEventTunnel() {
					return handler.tunnel().equalsIgnoreCase("default") ? EventTunnel.defaultTunnel
							: new EventTunnel(handler.tunnel());
				}

				public JSONObject getInfo() {
					return new JSONObject().put("listener", listener.toString()).put("method", method.getName());
				}

				public int getMinorRunLevel() {
					return handler.minorLevel();
				}

				public EventRunLevel getRunLevel() {
					return handler.mainLevel();
				}

				public boolean ignoreCanceled() {
					return handler.ignoreCanceled();
				}

				public EventRunResult run(Event event) {
					try {
						return (EventRunResult) method.invoke(listener, event);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
					return null;
				}
			});
		}
		return this;
	}

	/**
	 * 将runner注册到本EventBus
	 * 
	 * @param runner
	 * @return this
	 */
	public EventBus regRunner(EventRunner<?> runner) {
		runners.add(runner);
		runners.sort((r1, r2) -> {
			if (r1.getRunLevel().ordinal() > r2.getRunLevel().ordinal()) {
				return 1;
			} else if (r1.getRunLevel().ordinal() < r2.getRunLevel().ordinal()) {
				return -1;
			} else {
				if (r1.getMinorRunLevel() > r2.getMinorRunLevel()) {
					return 1;
				} else if (r1.getMinorRunLevel() < r2.getMinorRunLevel()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		return this;
	}

	/**
	 * 解除注册所有该Listener中可被解析为 {@link EventRunner} 的方法
	 * 
	 * @param listener
	 * @return this
	 */
	public EventBus unregListener(Listener listener) {
		unregRunner(r -> {
			if (r.getInfo() == null)
				return false;
			if (r.getInfo().optString("listener", "").equalsIgnoreCase(listener.toString()))
				return true;
			return false;
		});
		return this;
	}

	/**
	 * 解除注册 {@link EventRunner}: 遍历所有注册的 {@link EventRunner}并使用p进行测试
	 * 返回true时这解除注册该 {@link EventRunner}
	 * 
	 * @param p
	 * @return this
	 */
	public EventBus unregRunner(Predicate<EventRunner<?>> p) {
		List<EventRunner<?>> removeList = new ArrayList<>();
		for (EventRunner<?> r : runners) {
			if (p.test(r))
				removeList.add(r);
		}
		runners.removeAll(removeList);
		return this;
	}
}

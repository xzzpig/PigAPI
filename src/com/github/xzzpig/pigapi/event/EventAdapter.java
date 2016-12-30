package com.github.xzzpig.pigapi.event;


/**
 * @author xzzpig
 *用于接收Listener并可触发不同的事件
 */
public interface EventAdapter {
	public default void callEvent(Event event) {
		getEventInstance().callEvent_(event);
	}
	
	/**
	 * 对于同一对象调用该方法返回值每次应相同
	 * 对于不同对象应返回不同的Event实例
	 * @return Event实例
	 */
	public Event getEventInstance();

	public default void registListener(Listener listener) {
		getEventInstance().registListener_(listener);
	}

	public default void unregListener(Listener listener) {
		getEventInstance().unregListener_(listener);
	}
}

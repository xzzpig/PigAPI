package com.github.xzzpig.pigapi.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Event {
	private static final HashMap<Type, List<EventMethod>> events = new HashMap<Type, List<EventMethod>>();

	private static void addEvent(Listener listener, Method meth, Type type) {
		if (!events.containsKey(type))
			events.put(type, new ArrayList<EventMethod>());
		events.get(type).add(new EventMethod(meth, listener));
		EventMethod[] os = events.get(type).toArray(new EventMethod[0]);
		Arrays.sort(os);
		events.get(type).clear();
		events.get(type).addAll(Arrays.asList(os));
//		if (type == Event.class) {
//			return;
//		}
//		Class<?> su = ((Class<?>) type).getSuperclass();
//		addEvent(listener, meth, su);
	}

	public static final void callEvent(Event event) {
		Type eventtype = event.getClass();
		callEvent(event, eventtype);
	}

	private static final void callEvent(Event event, Type eventtype) {
		if (events.containsKey(eventtype)) {
			for (EventMethod em : events.get(eventtype)) {
				if (event.isCanceled() && !em.eventHandler.ignoreCanceled()) {
					continue;
				}
				try {
					em.method.invoke(em.listener, new Object[] { event });
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("触发事件错误");
				}
			}
		}
		if (eventtype == Event.class) {
			return;
		}
		callEvent(event, ((Class<?>) eventtype).getSuperclass());
	}

	public static final void registListener(Listener listener) {
		for (Method meth : listener.getClass().getMethods()) {
			Annotation ann = meth.getDeclaredAnnotation(EventHandler.class);
			if (ann != null) {
				meth.setAccessible(true);
				try {
					Type type = meth.getGenericParameterTypes()[0];
					addEvent(listener, meth, type);
				} catch (Exception e) {
				}
			}
		}
	}

	public static final void unregListener(Listener listener) {
		for (List<EventMethod> list : events.values()) {
			List<EventMethod> removeList = new ArrayList<>();
			for (EventMethod eventMethod : list) {
				if (eventMethod.listener == listener) {
					removeList.add(eventMethod);
				}
			}
			list.removeAll(removeList);
		}
	}

	private boolean cancel;

	public boolean isCanceled() {
		return cancel;
	}

	public void setCancel(boolean c) {
		cancel = c;
	}
}

class EventMethod implements Comparable<EventMethod> {
	EventHandler eventHandler;
	Listener listener;
	Method method;

	EventMethod(Method method, Listener listener) {
		this.method = method;
		this.listener = listener;
		eventHandler = this.method.getDeclaredAnnotation(EventHandler.class);
	}

	@Override
	public int compareTo(EventMethod arg0) {
		if (eventHandler.mainLevel().ordinal() > arg0.eventHandler.mainLevel().ordinal()) {
			return 1;
		} else if (eventHandler.mainLevel().ordinal() < arg0.eventHandler.mainLevel().ordinal()) {
			return -1;
		} else {
			if (eventHandler.minorLevel() > arg0.eventHandler.minorLevel()) {
				return 1;
			} else if (eventHandler.minorLevel() < arg0.eventHandler.minorLevel()) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

package com.github.xzzpig.pigapi.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Event {
	public static final EventHandler defaultEventHandler = new EventHandler() {
		@Override
		public Class<? extends Annotation> annotationType() {
			return EventHandler.class;
		}

		@Override
		public boolean ignoreCanceled() {
			return false;
		}

		@Override
		public EventRunLevel mainLevel() {
			return EventRunLevel.Normal;
		}

		@Override
		public int minorLevel() {
			return 0;
		}
	};

	private static final Event eventInstance = new Event();

	public static final void callEvent(Event event) {
		eventInstance.callEvent_(event);
	}

	public static final void registListener(Listener listener) {
		eventInstance.registListener_(listener);
	}

	public static final <T extends Event> void registListener(SimpleListener<T> listener) {
		registListener((Listener) listener);
	}

	public static final void unregListener(Listener listener) {
		eventInstance.unregListener_(listener);
	}

	private boolean cancel;

	private final HashMap<Type, List<EventMethod>> events = new HashMap<Type, List<EventMethod>>();

	private void addEvent(Listener listener, Method meth, Type type) {
		if (!events.containsKey(type))
			events.put(type, new ArrayList<EventMethod>());
		events.get(type).add(new EventMethod(meth, listener));
		EventMethod[] os = events.get(type).toArray(new EventMethod[0]);
		Arrays.sort(os);
		events.get(type).clear();
		events.get(type).addAll(Arrays.asList(os));
	}

	private final void callEvent(Event event, Type eventtype) {
		if (events.containsKey(eventtype)) {
			for (EventMethod em : events.get(eventtype)) {
				if (event.isCanceled() && !em.eventHandler.ignoreCanceled()) {
					continue;
				}
				try {
					em.method.invoke(em.listener, new Object[] { event });
				} catch (Exception e) {
					if (e instanceof InvocationTargetException)
						continue;
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

	final void callEvent_(Event event) {
		Type eventtype = event.getClass();
		callEvent(event, eventtype);
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}

	public boolean isCanceled() {
		return cancel;
	}

	final void registListener_(Listener listener) {
		for (Method meth : listener.getClass().getMethods()) {
			Annotation ann = meth.getDeclaredAnnotation(EventHandler.class);
			if (listener instanceof SimpleListener && meth.getName().equals("run")) {
				ann = ((SimpleListener<?>) listener).getHandler();
			}
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

	public void setCancel(boolean c) {
		cancel = c;
	}

	final void unregListener_(Listener listener) {
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
}

class EventMethod implements Comparable<EventMethod> {
	EventHandler eventHandler;
	Listener listener;
	Method method;

	EventMethod(Method method, Listener listener) {
		this.method = method;
		this.listener = listener;
		eventHandler = this.method.getDeclaredAnnotation(EventHandler.class);
		if (eventHandler == null) {
			eventHandler = Event.defaultEventHandler;
		}
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

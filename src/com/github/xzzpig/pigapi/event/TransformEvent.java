package com.github.xzzpig.pigapi.event;

public class TransformEvent<F, R> extends Event {
	public interface Transformer<F, R> {
		public R transform(F f);
	}

	public final F from;

	private R result;

	public TransformEvent(F from) {
		this.from = from;
	}

	public void setResult(R r) {
		result = r;
	}

	public R getResult() {
		return result;
	}

	public static <F, R> R transform(F from, Class<R> rc) {
		TransformEvent<F, R> tevent = new TransformEvent<F, R>(from);
		Event.callEvent(tevent);
		return tevent.getResult();
	}

	public static <F, R> void addTransformer(Transformer<F, R> transformer) {
		Event.registListener(new Listener() {
			@EventHandler
			public void onListen(Event event) {
				try {
					@SuppressWarnings("unchecked")
					TransformEvent<F, R> e = (TransformEvent<F, R>) event;
					R r = transformer.transform(e.from);
					if (r != null) {
						e.setResult(r);
						event.setCancel(true);
					}
				} catch (Exception e) {
				}
			}

			@Override
			public String getName() {
				return transformer.toString();
			}
		});
	}

	public static <F, R> void removeTransformer(Transformer<F, R> transformer) {
		Event.unregListener(l -> l.getName().equalsIgnoreCase(transformer.toString()));
	}
}

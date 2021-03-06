package com.github.xzzpig.pigutils.event;

import com.github.xzzpig.pigutils.json.JSONObject;

public class TransformEvent<F, R> extends Event {
	public interface Transformer<F, R> {
		public R transform(F f);
	}

	public static <F, R> void addTransformer(Transformer<F, R> transformer) {
		Event.regRunner(new EventRunner<TransformEvent<F, R>>() {
			@Override
			public JSONObject getInfo() {
				return new JSONObject().put("from", transformer.toString());
			}

			@Override
			public EventRunResult run(TransformEvent<F, R> event) {
				try {
					R r = transformer.transform(event.from);
					if (r != null) {
						event.setResult(r);
						event.setCanceled(true);
					}
				} catch (Exception e) {
				}
				return null;
			}
		});
	}

	public static <F, R> void removeTransformer(Transformer<F, R> transformer) {
		Event.unregRunner(r -> r.getInfo().optString("from", "").equalsIgnoreCase(transformer.toString()));
	}

	public static <F, R> R transform(F from, Class<R> rc) {
		TransformEvent<F, R> tevent = new TransformEvent<F, R>(from);
		Event.callEvent(tevent);
		return tevent.getResult();
	}

	public final F from;

	private R result;

	public TransformEvent(F from) {
		this.from = from;
	}

	public R getResult() {
		return result;
	}

	public void setResult(R r) {
		result = r;
	}
}

package com.github.xzzpig.pigapi.oldevent;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ java.lang.annotation.ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EventHandler {

	public enum EventRunLevel {
		High, Highest, Low, Lowest, Normal
	}

	public boolean ignoreCanceled() default false;

	public EventRunLevel mainLevel() default EventRunLevel.Normal;

	public int minorLevel() default 0;
}

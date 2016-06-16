package com.github.xzzpig.pigapi.bukkit.event;

import org.bukkit.entity.LivingEntity;

import com.github.xzzpig.pigapi.event.Event;

public class StringMatcherEvent extends Event {
	LivingEntity entity;
	String souce;

	public StringMatcherEvent(String souce, LivingEntity entity) {
		this.souce = souce;
		this.entity = entity;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public String getSouce() {
		return souce;
	}

	public void setEntity(LivingEntity entity) {
		this.entity = entity;
	}

	public void setSouce(String souce) {
		this.souce = souce;
	}
}

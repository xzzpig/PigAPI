package com.github.xzzpig.pigapi.bukkit.event;

import org.bukkit.entity.Player;

import com.github.xzzpig.pigapi.event.Event;

public class ChatMessageSendEvent extends Event {
	public String msg;
	public final Player from, to;
	public boolean highlight;

	public ChatMessageSendEvent(String msg, Player from, Player to) {
		this.msg = msg;
		this.from = from;
		this.to = to;
	}

	public String getMessage() {
		return msg;
	}

	public ChatMessageSendEvent setMessage(String msg) {
		this.msg = msg;
		return this;
	}
}

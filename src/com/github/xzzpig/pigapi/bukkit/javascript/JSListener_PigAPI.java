package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

import com.github.xzzpig.pigapi.bukkit.event.SimpleWebServeEvent;
import com.github.xzzpig.pigapi.bukkit.event.StringMatcherEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketCloseEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketErrorEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketMessageEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketOpenEvent;
import com.github.xzzpig.pigutils.event.EventHandler;
import com.github.xzzpig.pigutils.event.Listener;

public class JSListener_PigAPI implements Listener {
	public static JSListener_PigAPI instance = new JSListener_PigAPI();

	@EventHandler
	public void onEvent(PluginDisableEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PluginEnableEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(SimpleWebServeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(StringMatcherEvent event) {
		JSListener.runScript(event);
	}

	@com.github.xzzpig.pigutils.event.EventHandler
	public void onEvent(WebSocketCloseEvent event) {
		JSListener.runScript(event);
	}

	@com.github.xzzpig.pigutils.event.EventHandler
	public void onEvent(WebSocketErrorEvent event) {
		JSListener.runScript(event);
	}

	@com.github.xzzpig.pigutils.event.EventHandler
	public void onEvent(WebSocketMessageEvent event) {
		JSListener.runScript(event);
	}

	@com.github.xzzpig.pigutils.event.EventHandler
	public void onEvent(WebSocketOpenEvent event) {
		JSListener.runScript(event);
	}
}

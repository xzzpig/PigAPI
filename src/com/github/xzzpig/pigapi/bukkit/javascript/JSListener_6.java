package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerChannelEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JSListener_6 implements Listener, com.github.xzzpig.pigapi.event.Listener {
	public static final JSListener_6 instance = new JSListener_6();

	@EventHandler
	public void onEvent(PlayerChangedWorldEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerChannelEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerChatTabCompleteEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerCommandPreprocessEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerDeathEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerDropItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerEditBookEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerEggThrowEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerExpChangeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerFishEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerGameModeChangeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerInteractEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerInteractEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerItemBreakEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerItemConsumeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerItemHeldEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerJoinEvent event) {
		JSListener.runScript(event);
	}
}

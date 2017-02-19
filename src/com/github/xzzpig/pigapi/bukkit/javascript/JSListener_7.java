package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.event.player.PlayerUnleashEntityEvent;
import org.bukkit.event.player.PlayerUnregisterChannelEvent;

public class JSListener_7 implements Listener, com.github.xzzpig.pigapi.event.Listener {
	public static final JSListener_7 instance = new JSListener_7();

	@EventHandler
	public void onEvent(PlayerKickEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerLeashEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerLevelChangeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerLoginEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerMoveEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerPickupItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerPortalEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerQuitEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerRegisterChannelEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerRespawnEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerShearEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerStatisticIncrementEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerTeleportEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerToggleFlightEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerToggleSneakEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerToggleSprintEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerUnleashEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerUnregisterChannelEvent event) {
		JSListener.runScript(event);
	}
}

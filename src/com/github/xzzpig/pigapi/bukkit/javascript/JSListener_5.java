package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.entity.PigZapEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.weather.LightningStrikeEvent;

public class JSListener_5 implements Listener, com.github.xzzpig.pigutils.event.Listener {
	public static final JSListener_5 instance = new JSListener_5();

	@EventHandler
	public void onEvent(InventoryInteractEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryMoveItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryOpenEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryPickupItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ItemDespawnEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ItemSpawnEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(LeavesDecayEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(LightningStrikeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(MapInitializeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(NotePlayEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PigZapEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerAchievementAwardedEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerAnimationEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerBedEnterEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerBedLeaveEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerBucketEmptyEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(PlayerBucketFillEvent event) {
		JSListener.runScript(event);
	}
}

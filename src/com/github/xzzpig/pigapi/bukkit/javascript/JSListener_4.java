package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityUnleashEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceExtractEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class JSListener_4 implements Listener, com.github.xzzpig.pigapi.event.Listener {
	public static final JSListener_4 instance = new JSListener_4();

	@EventHandler
	public void onEvent(EntityUnleashEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ExpBottleEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ExplosionPrimeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(FoodLevelChangeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(FurnaceBurnEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(FurnaceExtractEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(FurnaceSmeltEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(HangingBreakByEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(HangingBreakEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(HangingPlaceEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(HorseJumpEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryClickEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryCloseEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryCreativeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(InventoryDragEvent event) {
		JSListener.runScript(event);
	}
}

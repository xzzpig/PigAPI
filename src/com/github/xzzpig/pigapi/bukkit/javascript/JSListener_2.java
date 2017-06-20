package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.block.EntityBlockFormEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreeperPowerEvent;
import org.bukkit.event.entity.EntityBreakDoorEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class JSListener_2 implements Listener, com.github.xzzpig.pigutils.event.Listener {
	public static final JSListener_2 instance = new JSListener_2();

	@EventHandler
	public void onEvent(BlockPlaceEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(BlockRedstoneEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(BlockSpreadEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(BrewEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ChunkLoadEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ChunkPopulateEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ChunkUnloadEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(CraftItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(CreatureSpawnEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(CreeperPowerEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EnchantItemEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityBlockFormEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityBreakDoorEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityChangeBlockEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityCombustByBlockEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityCombustByEntityEvent event) {
		JSListener.runScript(event);
	}
}

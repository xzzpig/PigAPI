package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTeleportEvent;

public class JSListener_3 implements Listener, com.github.xzzpig.pigapi.event.Listener {
	public static final JSListener_3 instance = new JSListener_3();

	@EventHandler
	public void onEvent(EntityCombustEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityCreatePortalEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityDamageByBlockEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityDamageByEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityDamageEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityDeathEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityExplodeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityInteractEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityPortalEnterEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityPortalEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityPortalExitEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityRegainHealthEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityShootBowEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityTameEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityTargetEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityTargetLivingEntityEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(EntityTeleportEvent event) {
		JSListener.runScript(event);
	}
}

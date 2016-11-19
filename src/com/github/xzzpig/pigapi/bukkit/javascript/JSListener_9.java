package com.github.xzzpig.pigapi.bukkit.javascript;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import com.github.xzzpig.pigapi.bukkit.event.StringMatcherEvent;

public class JSListener_9 implements Listener, com.github.xzzpig.pigapi.event.Listener {
	public static final JSListener_9 instance = new JSListener_9();
	
	@com.github.xzzpig.pigapi.event.EventHandler
	public void onEvent(StringMatcherEvent event) {
		JSListener.runScript(event);
	}

	
	@EventHandler
	public void onEvent(StructureGrowEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(ThunderChangeEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleBlockCollisionEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleCreateEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleDamageEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleDestroyEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleEnterEvent event) {
		JSListener.runScript(event);
	}
	
	@EventHandler
	public void onEvent(VehicleEntityCollisionEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleExitEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleMoveEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(VehicleUpdateEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(WeatherChangeEvent event) {
		JSListener.runScript(event);
	}


	@EventHandler
	public void onEvent(WorldInitEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(WorldLoadEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(WorldSaveEvent event) {
		JSListener.runScript(event);
	}

	@EventHandler
	public void onEvent(WorldUnloadEvent event) {
		JSListener.runScript(event);
	}
}

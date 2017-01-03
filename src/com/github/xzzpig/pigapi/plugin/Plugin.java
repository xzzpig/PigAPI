package com.github.xzzpig.pigapi.plugin;

public abstract class Plugin {
	PluginInfo pluginInfo;

	public PluginInfo getPluginInfo() {
		return pluginInfo;
	}

	public abstract void onDisable();

	public abstract void onEnable();
}

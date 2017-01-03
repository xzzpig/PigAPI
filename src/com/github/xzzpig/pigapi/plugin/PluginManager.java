package com.github.xzzpig.pigapi.plugin;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class PluginManager {

	static List<PluginInfo> handleplugins = new ArrayList<>();
	static Map<String, Plugin> plugins = new HashMap<>();

	public static void loadHandlePlugin() {
		List<PluginInfo> list = new ArrayList<>(handleplugins);
		list.forEach((PluginInfo info) -> {
			boolean load = loadPlugin(info);
			if (load) {
				handleplugins.remove(info);
			}
		});
	}

	@SuppressWarnings({ "unchecked", "resource" })
	public static boolean loadJarPlugin(File jar) {
		loadHandlePlugin();
		try {
			JarFile jarFile;
			jarFile = new JarFile(jar);
			ZipEntry jarEntry = jarFile.getEntry("info.json");
			PluginInfo info;
			InputStream inputStream = jarFile.getInputStream(jarEntry);
			info = new PluginInfo(inputStream);
			jarFile.close();
			info.loader = new PluginLoader() {
				@Override
				public Plugin loadPlugin() {
					try {
						URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { jar.toURI().toURL() });
						String main = info.getMain();
						Class<? extends Plugin> c = (Class<? extends Plugin>) urlClassLoader.loadClass(main);
						Plugin plugin = c.newInstance();
						plugin.pluginInfo = info;
						return plugin;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			};
			return loadPlugin(info);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean loadPlugin(PluginInfo info) {
		info.getDependence().forEach((String name) -> {
			if (plugins.containsKey(name)) {
				handleplugins.add(info);
			}
		});
		if (handleplugins.contains(info)) {
			return false;
		}
		Plugin plugin = info.loader.loadPlugin();
		if (plugin == null) {
			return false;
		}
		plugin.onEnable();
		plugins.put(plugin.pluginInfo.getName(), plugin);
		return true;
	}

	public static void unloadPlugin(String name) {
		if (!plugins.containsKey(name)) {
			return;
		}
		Plugin plugin = plugins.get(name);
		plugin.onDisable();
		plugins.remove(name);
	}

	private PluginManager() {
	}
}
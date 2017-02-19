package com.github.xzzpig.pigapi.bukkit.javascript;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.xzzpig.pigapi.TUrl;
import com.github.xzzpig.pigapi.bukkit.TConfig;
import com.github.xzzpig.pigapi.json.JSONObject;
import com.github.xzzpig.pigapi.plugin.Main;

public class JSPlugin {
	private static FileConfiguration jspluginconfig;

	private static JSONObject weblist;

	public static void freshPluginState() {
		for (String plugin : getJsPluginList()) {
			if (jspluginconfig.getBoolean(plugin + ".enable")) {
				if (!jspluginconfig.getBoolean(plugin + ".enabled")) {
					installJsPlugin(plugin);
				} else if (needUpdateJsPlugin(plugin)) {
					updateJsPlugin(plugin);
				}
			} else if (jspluginconfig.getBoolean(plugin + ".enabled")) {
				unstallJsPlugin(plugin);
			}
		}
	}

	public static List<String> getJsPluginList() {
		loadList();
		return new ArrayList<>(weblist.keySet());
	}

	public static String getJsPluginVersionOnWeb(String plugin) {
		return TUrl.getHtml("http://www.xzzpig.com/jsplugin/" + plugin + "/version.txt").replace("\n", "")
				.replaceAll(" ", "");
	}

	public static void installJsPlugin(String plugin) {
		System.out.println("[PigAPI]开始安装插件" + plugin);
		loadList();
		JSONObject pluginList = weblist.optJSONObject(plugin);
		for (String event : pluginList.keySet()) {
			if (event.equalsIgnoreCase("."))
				continue;
			File eventdir = new File(Main.self.getDataFolder(), "scripts/" + event);
			if ((!eventdir.exists()) || eventdir.isFile()) {
				eventdir.delete();
				eventdir.mkdirs();
			}
			List<Object> jsList = pluginList.optJSONObject(event).optJSONArray(".").toList();
			for (Object js : jsList) {
				File jsFile = new File(eventdir, plugin + "_" + js);
				FileWriter writer;
				try {
					writer = new FileWriter(jsFile);
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
				String jsString = TUrl.getHtml("http://www.xzzpig.com/jsplugin/" + plugin + "/" + event + "/" + js);
				try {
					writer.write(jsString);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.print(".");
			}
		}
		jspluginconfig.set(plugin + ".version", getJsPluginVersionOnWeb(plugin));
		jspluginconfig.set(plugin + ".enabled", true);
		saveConfig();
		System.out.println("[PigAPI]插件" + plugin + "安装完成");
		JSListener.instance.loadScript();
	}

	public static void loadList() {
		if (weblist == null) {
			jspluginconfig = TConfig.getConfigFile("PigAPI", "jsconfig.yml");
			System.out.println("[PigAPI]开始加载插件列表清单");
			weblist = new JSONObject(TUrl.getHtml("http://www.xzzpig.com/jsplugin/index.php?command=list"));
			System.out.println("[PigAPI]插件列表清单获取完成");
			for (String plugin : getJsPluginList()) {
				if (jspluginconfig.contains(plugin))
					continue;
				jspluginconfig.set(plugin + ".enable", false);
				jspluginconfig.set(plugin + ".enabled", false);
				jspluginconfig.set(plugin + ".version", "unknown");
			}
			saveConfig();
		}
	}

	private static boolean needUpdateJsPlugin(String plugin) {
		return !jspluginconfig.getString(plugin + ".version", "unknown")
				.equalsIgnoreCase(getJsPluginVersionOnWeb(plugin));
	}

	private static void saveConfig() {
		TConfig.saveConfig("PigAPI", jspluginconfig, "jsconfig.yml");
	}

	public static void unstallJsPlugin(String plugin) {
		System.out.println("[PigAPI]开始卸载插件" + plugin);
		loadList();
		JSONObject pluginList = weblist.optJSONObject(plugin);
		for (String event : pluginList.keySet()) {
			if (event.equalsIgnoreCase("."))
				continue;
			File eventdir = new File(Main.self.getDataFolder(), "scripts/" + event);
			if ((!eventdir.exists()) || eventdir.isFile()) {
				eventdir.delete();
				continue;
			}
			List<Object> jsList = pluginList.optJSONObject(event).optJSONArray(".").toList();
			for (Object js : jsList) {
				File jsFile = new File(eventdir, plugin + "_" + js);
				jsFile.delete();
				System.out.print(".");
			}
		}
		jspluginconfig.set(plugin + ".enabled", false);
		saveConfig();
		System.out.println("[PigAPI]插件" + plugin + "卸载完成");
		JSListener.instance.loadScript();
	}

	public static void updateJsPlugin(String plugin) {
		System.out.println("[PigAPI]开始更新插件" + plugin);
		unstallJsPlugin(plugin);
		installJsPlugin(plugin);
		System.out.println("[PigAPI]插件" + plugin + "更新完成");
	}
}

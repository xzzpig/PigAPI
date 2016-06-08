package com.github.xzzpig.pigapi.plugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.xzzpig.pigapi.Debuger;
import com.github.xzzpig.pigapi.PigData;
import com.github.xzzpig.pigapi.TData;
import com.github.xzzpig.pigapi.bukkit.TConfig;
import com.github.xzzpig.pigapi.bukkit.TPrefix;

public class Main extends JavaPlugin {

	public static Main self;

	public static SaveThread autoSavePrefix = new SaveThread();

	@Override
	public void onEnable() {
		self = this;
		TPrefix.autoSave = false;
		getLogger().info(getName() + getDescription().getVersion() + "插件已被加载");
		saveDefaultConfig();
		Debuger.debug = true;
		Vars.config = TConfig.getConfigFile("PigAPI", "config.yml");
		Vars.dataFile = new File(getDataFolder(), "data.pigdata");
		try {
			Vars.dataFile.createNewFile();
			Vars.pigData = new PigData(Vars.dataFile);
			Vars.prefix = new TData(Vars.pigData.getString("prefix"));
			// Debuger.print(Vars.prefix.getStrings());
		} catch (Exception e) {
			Vars.pigData = new PigData();
			Vars.prefix = new TData();
			TPrefix.setPrefix("default", "default");
		}
		Vars.enable_chatmanager = Vars.config.getBoolean(
				"PigAPI.enable.chatmanager", true);
		if (Vars.enable_chatmanager) {
			enableChatManager();
		}
	}

	private boolean ench;

	public void enableChatManager() {
		if (ench)
			return;
		getServer().getPluginManager().registerEvents(ChatManager.self, this);
		Vars.chatformat = Vars.config.getString(
				"PigAPI.chatmanager.chatformat",
				"&2</world/></n/></prefix/></colorid/>&r:</message/>");
		getLogger().info("ChatManager启动");
		getLogger().info("聊天格式:" + Vars.chatformat);
		autoSavePrefix.start();
		ench = true;
	}

	// 插件停用函数
	@Override
	public void onDisable() {
		autoSavePrefix.finish();
		getLogger().info(getName() + "插件已被停用 ");
	}
}

class SaveThread extends Thread {
	private boolean go = true;

	@Override
	public void run() {
		while (go) {
			TPrefix.savePrefix();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void finish() {
		go = false;
	}
}
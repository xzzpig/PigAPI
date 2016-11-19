package com.github.xzzpig.pigapi.plugin;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.xzzpig.pigapi.Debuger;
import com.github.xzzpig.pigapi.PigData;
import com.github.xzzpig.pigapi.bukkit.TCommandHelp;
import com.github.xzzpig.pigapi.bukkit.TConfig;
import com.github.xzzpig.pigapi.bukkit.TPrefix;
import com.github.xzzpig.pigapi.bukkit.event.PluginLoadEvent;
import com.github.xzzpig.pigapi.bukkit.event.PluginUnLoadEvent;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_1;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_2;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_3;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_4;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_5;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_6;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_7;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_8;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_9;
import com.github.xzzpig.pigapi.bukkit.javascript.JSPlugin;
import com.github.xzzpig.pigapi.event.Event;

public class Main extends JavaPlugin {

	public static Main self;

	public static SaveThread autoSavePrefix = new SaveThread();

	@Override
	public void onEnable() {
		self = this;
		TPrefix.autoSave = false;
		getLogger().info(getName() + getDescription().getVersion() + "插件已被加载");
		new Thread(()->{
			JSPlugin.freshPluginState();			
		}).start();;
		getServer().getPluginManager().registerEvents(JSListener.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_1.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_2.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_3.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_4.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_5.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_6.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_7.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_8.instance, this);
		getServer().getPluginManager().registerEvents(JSListener_9.instance, this);
		Event.registListener(JSListener.instance);
		Event.registListener(JSListener_1.instance);
		Event.registListener(JSListener_2.instance);
		Event.registListener(JSListener_3.instance);
		Event.registListener(JSListener_4.instance);
		Event.registListener(JSListener_5.instance);
		Event.registListener(JSListener_6.instance);
		Event.registListener(JSListener_7.instance);
		Event.registListener(JSListener_8.instance);
		Event.registListener(JSListener_9.instance);
		saveDefaultConfig();
		Debuger.debug = true;
		Vars.config = TConfig.getConfigFile("PigAPI", "config.yml");
		Vars.dataFile = new File(getDataFolder(), "data.pigdata");
		try {
			Vars.dataFile.createNewFile();
			Vars.pigData = new PigData(Vars.dataFile);
			Vars.prefix = Vars.pigData.getSub("prefix");
			// Debuger.print(Vars.prefix.getStrings());
		} catch (Exception e) {
			Vars.pigData = new PigData();
			Vars.prefix = Vars.pigData.getSub("prefix");
			TPrefix.setPrefix("default", "default");
		}
		Vars.enable_chatmanager = Vars.config.getBoolean("PigAPI.enable.chatmanager", true);
		if (Vars.enable_chatmanager) {
			enableChatManager();
		}
		Event.callEvent(new PluginLoadEvent());
	}

	private boolean ench;

	public void enableChatManager() {
		if (ench)
			return;
		getServer().getPluginManager().registerEvents(ChatManager.self, this);
		Vars.chatformat = Vars.config.getString("PigAPI.chatmanager.chatformat",
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
		Event.callEvent(new PluginUnLoadEvent());
		getLogger().info(getName() + "插件已被停用 ");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String arg0 = "help";
		try {
			arg0 = args[0];
		} catch (Exception e) {
		}
		if (arg0.equalsIgnoreCase("help")) {
			if (arg0.equalsIgnoreCase("help")) {
				for (TCommandHelp sub : Help.PigAPI.getSubCommandHelps()) {
					sub.getHelpMessage("PigAPI").send(sender);
				}
				return true;
			}
		} else if (arg0.equalsIgnoreCase("reloadscript")) {
			if (!sender.isOp()) {
				sender.sendMessage("[PigAPI]" + ChatColor.RED + "你没有权限执行该命令");
				return true;
			}
			JSListener.instance.loadScript();
			sender.sendMessage("[PigAPI]" + ChatColor.GREEN + "JS脚本重载成功");
			return true;
		}
		return false;
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
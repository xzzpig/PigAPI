package com.github.xzzpig.pigapi.plugin;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.github.xzzpig.pigapi.Debuger;
import com.github.xzzpig.pigapi.PigData;
import com.github.xzzpig.pigapi.bukkit.TConfig;
import com.github.xzzpig.pigapi.bukkit.TPrefix;
import com.github.xzzpig.pigapi.bukkit.event.PluginLoadEvent;
import com.github.xzzpig.pigapi.bukkit.event.PluginUnLoadEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketCloseEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketErrorEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketMessageEvent;
import com.github.xzzpig.pigapi.bukkit.event.WebSocketOpenEvent;
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
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener_PigAPI;
import com.github.xzzpig.pigapi.bukkit.javascript.JSPlugin;
import com.github.xzzpig.pigapi.event.Event;
import com.github.xzzpig.pigapi.pigsimpleweb.PigSimpleWebServer;

public class Main extends JavaPlugin {

	public static SaveThread autoSavePrefix = new SaveThread();

	public static Main self;

	private boolean ench, enjs, enws, enwsr;
	public PigSimpleWebServer webserver;

	public WebSocketServer wsserver;

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

	public void enableJsPlugin() {
		if (enjs)
			return;
		getLogger().info("JsPlugin启动");
		new Thread(() -> {
			JSPlugin.freshPluginState();
		}).start();
		;
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
		Event.registListener(JSListener_PigAPI.instance);
		enjs = true;
	}

	public void enableWebServer() {
		if (enwsr)
			return;
		webserver = new PigSimpleWebServer(Vars.web_port);
		try {
			webserver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		getLogger().info("SimpleWebServer启动于端口:" + Vars.web_port);
		enwsr = true;
	}

	public void enableWebSocket() {
		if (enws)
			return;
		getLogger().info("WebSocket启动于端口:" + Vars.ws_port);
		wsserver = new WebSocketServer(new InetSocketAddress(Vars.ws_port)) {
			@Override
			public void onClose(WebSocket conn, int code, String reason, boolean remote) {
				Event.callEvent(new WebSocketCloseEvent(wsserver, conn, code, reason, remote));
			}

			@Override
			public void onError(WebSocket conn, Exception ex) {
				Event.callEvent(new WebSocketErrorEvent(wsserver, conn, ex));
			}

			@Override
			public void onMessage(WebSocket conn, String message) {
				Event.callEvent(new WebSocketMessageEvent(wsserver, conn, message));

			}

			@Override
			public void onOpen(WebSocket conn, ClientHandshake handshake) {
				Event.callEvent(new WebSocketOpenEvent(wsserver, conn, handshake));
			}
		};
		wsserver.start();
		enws = true;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return Help.PigAPI.runCommand(Help.PigAPI.new CommandInstance(sender, command, label, args));
	}

	// 插件停用函数
	@Override
	public void onDisable() {
		autoSavePrefix.finish();
		if (webserver != null)
			webserver.stop();
		Event.callEvent(new PluginUnLoadEvent());
		getLogger().info(getName() + "插件已被停用 ");
	}

	@Override
	public void onEnable() {

		self = this;
		TPrefix.autoSave = false;
		saveDefaultConfig();
		getLogger().info(getName() + getDescription().getVersion() + "插件已被加载");
		Debuger.debug = true;
		Vars.config = TConfig.getConfigFile("PigAPI", "config.yml");
		Vars.dataFile = new File(getDataFolder(), "data.pigdata");
		try {
			Vars.dataFile.createNewFile();
			Vars.pigData = new PigData(Vars.dataFile);
			Vars.prefix = Vars.pigData.getSub("prefix");
		} catch (Exception e) {
			Vars.pigData = new PigData();
			Vars.prefix = Vars.pigData.getSub("prefix");
			TPrefix.setPrefix("default", "default");
		}
		Vars.enable_chatmanager = Vars.config.getBoolean("PigAPI.enable.chatmanager", true);
		if (Vars.enable_chatmanager) {
			enableChatManager();
		}
		Vars.enable_jsplugin = Vars.config.getBoolean("PigAPI.enable.jsplugin", true);
		if (Vars.enable_jsplugin) {
			enableJsPlugin();
		}
		Vars.enable_websocket = Vars.config.getBoolean("PigAPI.enable.websocket", true);
		if (Vars.enable_websocket) {
			Vars.ws_port = Vars.config.getInt("PigAPI.websocket.port", 10727);
			enableWebSocket();
		}
		Vars.enable_webserver = Vars.config.getBoolean("PigAPI.enable.simplewebserver", true);
		if (Vars.enable_webserver) {
			Vars.web_port = Vars.config.getInt("PigAPI.simplewebserver.port", 80);
			enableWebServer();
		}
		Event.callEvent(new PluginLoadEvent());
	}
}

class SaveThread extends Thread {
	private boolean go = true;

	public void finish() {
		go = false;
	}

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
}
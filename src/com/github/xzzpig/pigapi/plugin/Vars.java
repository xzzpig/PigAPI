package com.github.xzzpig.pigapi.plugin;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.xzzpig.pigapi.PigData;

public class Vars {
	public static FileConfiguration config;
	public static boolean enable_chatmanager,enable_jsplugin,enable_websocket,enable_webserver;
	public static int ws_port,web_port;
	public static File dataFile;
	public static PigData pigData;
	public static PigData prefix;
	public static String chatformat;
}
package com.github.xzzpig.pigapi.plugin;

import com.github.xzzpig.pigapi.bukkit.TCommandHelp;

public class Help {
	public static TCommandHelp PigAPI = new TCommandHelp("pigapi", "PigAPI的主命令", "/pigapi help");

	static {
		PigAPI.addSubCommandHelp("reloadscript", "重新加载脚本文件", null, null);
	}
}

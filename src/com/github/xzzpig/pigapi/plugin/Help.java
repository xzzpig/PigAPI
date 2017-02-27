package com.github.xzzpig.pigapi.plugin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.xzzpig.pigapi.bukkit.TCommandHelp;
import com.github.xzzpig.pigapi.bukkit.TCommandHelp.CommandInstance;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener;

public class Help {
	public static TCommandHelp PigAPI = new TCommandHelp("pigapi", "PigAPI的主命令", "/pigapi help");

	public static TCommandHelp PigAPI_reloadscript = PigAPI.addSubCommandHelp("reloadscript", "重新加载脚本文件", null, null)
			.setCommandRunner(Help::pigapi_reloadscript);

	public static boolean pigapi_reloadscript(CommandInstance ci) {
		CommandSender sender = ci.sender;
		if (!sender.isOp()) {
			sender.sendMessage("[PigAPI]" + ChatColor.RED + "你没有权限执行该命令");
			return true;
		}
		JSListener.instance.loadScript();
		sender.sendMessage("[PigAPI]" + ChatColor.GREEN + "JS脚本重载成功");
		return true;
	}
}

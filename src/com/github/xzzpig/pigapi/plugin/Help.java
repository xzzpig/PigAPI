package com.github.xzzpig.pigapi.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.github.xzzpig.pigapi.TScript;
import com.github.xzzpig.pigapi.bukkit.TCommandHelp;
import com.github.xzzpig.pigapi.bukkit.TCommandHelp.CommandInstance;
import com.github.xzzpig.pigapi.bukkit.javascript.JSListener;

public class Help {
	public static TCommandHelp PigAPI = new TCommandHelp("pigapi", "PigAPI的主命令", "/pigapi help");

	public static TCommandHelp PigAPI_reloadscript = PigAPI.addSubCommandHelp("reloadscript", "重新加载脚本文件", null, null)
			.setCommandRunner(Help::pigapi_reloadscript).setPermission("pigapi.command.reloadscript");
	public static TCommandHelp PigAPI_runscript = PigAPI.addSubCommandHelp("runscript", "执行脚本文件", null, null)
			.setCommandRunner(Help::pigapi_runscript).setPermission("pigapi.command.runscript");

	public static boolean pigapi_reloadscript(CommandInstance ci) {
		CommandSender sender = ci.sender;
		JSListener.instance.loadScript();
		sender.sendMessage("[PigAPI]" + ChatColor.GREEN + "JS脚本重载成功");
		return true;
	}

	private static final File scriptDir = new File(Main.self.getDataFolder(), "scripts");

	public static boolean pigapi_runscript(CommandInstance ci) {
		if (ci.args.length < 2) {
			ci.sendMsg("[Script]不可为空");
			return false;
		}
		if (scriptDir.exists())
			scriptDir.mkdirs();
		String script = ci.args[1];
		File script_f = new File(scriptDir, script);
		ScriptEngine eng = null;
		if (script.endsWith(".py")) {
			eng = TScript.getJythonScriptEngine();
			if (eng == null) {
				ci.sendMsg("Jython脚本引擎未加载");
				return true;
			}
		} else if (script.endsWith(".js")) {
			eng = TScript.getJavaScriptEngine();
			if (eng == null) {
				ci.sendMsg("JavaScript脚本引擎未加载");
				return true;
			}
		}
		if (eng == null) {
			ci.sendMsg("无法识别该脚本格式");
			return true;
		}
		eng.put("plugin", Main.self);
		eng.put("ci", ci);
		eng.put("player", ci.sender);
		FileReader fr;
		try {
			fr = new FileReader(script_f);
			eng.eval(fr);
		} catch (FileNotFoundException e) {
			ci.sendMsg("该文件不存在");
			return true;
		} catch (ScriptException e) {
			e.printStackTrace();
			ci.sendMsg("Script执行失败,Reason:" + e.toString());
		}
		return true;
	}
}

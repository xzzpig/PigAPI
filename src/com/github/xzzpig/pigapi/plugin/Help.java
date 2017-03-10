package com.github.xzzpig.pigapi.plugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static TCommandHelp PigAPI_chat = PigAPI.addSubCommandHelp("chat", "聊天管理命令", "/pigapi chat help", null);

	public static TCommandHelp PigAPI_chat_desee = PigAPI_chat.addSubCommandHelp("desee", "管理聊天屏蔽",
			"/pigapi chat desee help", null);

	public static TCommandHelp PigAPI_chat_desee_add = PigAPI_chat_desee
			.addSubCommandHelp("add", "将某人添加至自己的聊天屏蔽列表", "[player]-玩家ID", "[player]")
			.setCommandRunner(Help::pigapi_chat_desee_add).addLimit(TCommandHelp.isPlayer);

	public static TCommandHelp PigAPI_chat_desee_remove = PigAPI_chat_desee
			.addSubCommandHelp("remove", "将某人移出自己的聊天屏蔽列表", "[player]-玩家ID", "[player]")
			.setCommandRunner(Help::pigapi_chat_desee_remove).addLimit(TCommandHelp.isPlayer);

	public static TCommandHelp PigAPI_chat_desee_list = PigAPI_chat_desee
			.addSubCommandHelp("list", "列出自己的聊天屏蔽列表", null, null).setCommandRunner(Help::pigapi_chat_desee_list)
			.addLimit(TCommandHelp.isPlayer);

	public static final Map<String, List<String>> deseemap = new HashMap<>();

	public static boolean pigapi_chat_desee_add(CommandInstance ci) {
		String sender = ci.sender.getName();
		if (ci.args.length < 4) {
			ci.sendMsg("&4[player]不能为空");
			return false;
		}
		String target = ci.args[3];
		List<String> desee = deseemap.containsKey(sender) ? deseemap.get(sender) : new ArrayList<>();
		desee.add(target);
		deseemap.put(sender, desee);
		ci.sendMsg("已将" + target + "加入你的聊天屏蔽列表");
		return true;
	}

	public static boolean pigapi_chat_desee_remove(CommandInstance ci) {
		String sender = ci.sender.getName();
		if (ci.args.length < 4) {
			ci.sendMsg("&4[player]不能为空");
			return false;
		}
		String target = ci.args[3];
		List<String> desee = deseemap.containsKey(sender) ? deseemap.get(sender) : new ArrayList<>();
		if (!desee.contains(target)) {
			ci.sendMsg(target + "不在你的聊天屏蔽列表中");
			return true;
		}
		desee.remove(target);
		deseemap.put(sender, desee);
		ci.sendMsg("已将" + target + "移出你的聊天屏蔽列表");
		return true;
	}

	public static boolean pigapi_chat_desee_list(CommandInstance ci) {
		String sender = ci.sender.getName();
		List<String> desee = deseemap.containsKey(sender) ? deseemap.get(sender) : new ArrayList<>();
		ci.sendMsg("你的聊天屏蔽列表:");
		desee.forEach(ci.sender::sendMessage);
		return true;
	}

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

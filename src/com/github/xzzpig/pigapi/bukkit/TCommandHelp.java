
package com.github.xzzpig.pigapi.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCommandHelp {
	private String command, describe, useage, var;
	private List<TCommandHelp> subs = new ArrayList<TCommandHelp>();
	private TCommandHelp uphelp;

	public TCommandHelp(String command, String describe, String useage) {
		this.command = command;
		this.describe = describe;
		this.useage = useage;
	}

	private TCommandHelp(String command, String describe, String useage,
			String var, TCommandHelp uphelp) {
		if (command == null)
			command = "error";
		this.command = command;
		if (describe == null)
			describe = "无";
		this.describe = describe;
		if (useage == null)
			useage = "无";
		this.useage = useage;
		if (var == null)
			var = "";
		this.var = var;
		this.uphelp = uphelp;
	}

	public String getCommand() {
		return command;
	}

	public String getDescribe() {
		return describe;
	}

	public String getUseage() {
		return useage;
	}

	public String getVar() {
		return var;
	}

	public static TCommandHelp valueOf(TCommandHelp basichelp, String command) {
		String[] cmds = command.split(" ");
		for(int i=cmds.length-1;i>=0;i--){
			String cmd = cmds[0];
			for(int i2=1;i2<=i;i2++){
				cmd = cmd + " "+cmds[i2];
			}
			for (TCommandHelp ch : basichelp.getAllSubs())
				if (ch.toString().equalsIgnoreCase(cmd))
					return ch;
		}
		return basichelp;
	}

	public TCommandHelp getFinalUpHelp() {
		TCommandHelp ch = this;
		while (ch.uphelp != null)
			ch = ch.uphelp;
		return ch;
	}

	public TMessage getHelpMessage(String pluginname) {
		TMessage help = new TMessage(TString
				.Prefix(pluginname, 3) + "/");
		String parts[] = this.toStrings();
		String com = "";
		for (String arg : parts) {
			com = com + arg;
			TCommandHelp ch = TCommandHelp.valueOf(this.getFinalUpHelp(), com);
			help.then(arg)
					.suggest("/" + com)
					.tooltip(
							ChatColor.GREEN + ch.command + " " + var + "\n"
									+ ChatColor.BLUE + ch.describe + "\n"
									+ ChatColor.GRAY + ch.useage).then(" ");
			com = com + " ";
		}
		help.then(ChatColor.BLUE + " -" + describe)
				.tooltip(ChatColor.GRAY + useage)
				.then("  " + ChatColor.GREEN + "" + ChatColor.UNDERLINE + "点我")
				.suggest("/" + command + " " + var)
				.tooltip("快速匹配命令\n" + "/" + command + " " + var);
		return help;
	}

	public TCommandHelp addSubCommandHelp(String command, String describe,
			String useage, String var) {
		TCommandHelp sub = new TCommandHelp(this.command + " " + command,
				describe, useage, var, this);
		subs.add(sub);
		return sub;
	}

	public TCommandHelp getSubCommandHelp(String command) {
		for (TCommandHelp c : subs) {
			if (command.equalsIgnoreCase(c.toString()))
				return c;
			if (c.toStrings()[c.toStrings().length - 1]
					.equalsIgnoreCase(command))
				return c;
		}
		return this;
	}

	public TCommandHelp[] getSubCommandHelps() {
		return subs.toArray(new TCommandHelp[0]);
	}

	public List<TCommandHelp> getAllSubs() {
		List<TCommandHelp> sublist = new ArrayList<TCommandHelp>();
		for (TCommandHelp pre : this.subs) {
			sublist.add(pre);
			List<TCommandHelp> sub = pre.getAllSubs();
			if (sub != null) {
				for (TCommandHelp sub2 : sub) {
					if (!sublist.contains(sub2))
						sublist.add(sub2);
				}
			}
		}
		return sublist;
	}

	@Override
	public String toString() {
		return command;
	}

	public String[] toStrings() {
		return command.split(" ");
	}
	
	public List<String> getTabComplete(String pluginname,CommandSender sender, Command command,
			String alias, String[] args) {
		//Debuger.print(command.getName()+"|"+alias+"|"+Arrays.toString(args));
		List<String> tab = new ArrayList<String>();
		String cmd = command.getName();
		for(String arg:args){
			cmd = cmd +" " + arg;
		}
		if(cmd.endsWith(" "))
			cmd = cmd.substring(0,cmd.length()-1);
		for(TCommandHelp help:TCommandHelp.valueOf(this,cmd).getSubCommandHelps()){
			tab.add(help.toStrings()[help.toStrings().length-1]);
		}
		List<String> tab2 = new ArrayList<String>();
		for(String str:tab){
			if(str.contains(args[args.length-1])){
				tab2.add(str);
			}
		}
		if(!tab2.isEmpty())
			tab = tab2;
		for(String str:tab)
			TCommandHelp.valueOf(this,cmd).getSubCommandHelp(str).getHelpMessage(pluginname).send((Player)sender);
		if(tab.isEmpty())
			tab.add(TCommandHelp.valueOf(this,cmd).getVar());
		return tab;
	}
}

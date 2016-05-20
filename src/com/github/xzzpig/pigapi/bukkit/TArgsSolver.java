package com.github.xzzpig.pigapi.bukkit;

import org.bukkit.ChatColor;

import com.github.xzzpig.pigapi.TData;

public class TArgsSolver {
	private TData data = new TData();

	public TArgsSolver(String arg) {
		for (ChatColor c : ChatColor.values())
			arg.replaceAll(c.toString(), "");
		String[] args = arg.split(" ");
		for (String set : args) {
			if (set.startsWith("-")) {
				String key = TString.sub(set, "-", ":");
				String value = set.replaceAll("-" + key + ":", "");
				data.setString(key, value);
			}
		}
	}

	public TArgsSolver(String[] args) {
		for (String set : args) {
			for (ChatColor c : ChatColor.values())
				set.replaceAll(c.toString(), "");
			if (set.startsWith("-")) {
				String key = TString.sub(set, "-", ":");
				String value = set.replaceAll("-" + key + ":", "");
				data.setString(key, value);
			}
		}
	}

	public String get(String key) {
		return data.getString(key);
	}

	public String[] keys() {
		return this.data.getStrings().keySet().toArray(new String[0]);
	}
}

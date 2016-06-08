package com.github.xzzpig.pigapi.bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.xzzpig.pigapi.plugin.Vars;

public class TPrefix {
	public static boolean autoSave = true;

	public static void setPrefix(String player, String prefix, String id) {
		if (id == null || id.equalsIgnoreCase(""))
			id = "default";
		Vars.prefix.setString(player + "$" + id, prefix);
		if (autoSave)
			savePrefix();
	}

	public static void setPrefix(String player, String prefix) {
		setPrefix(player, prefix, "default");
	}

	public static void removePrefix(String player, String id) {
		Vars.prefix.getStrings().remove(player + "$" + id);
		if (autoSave)
			savePrefix();
	}

	public static void removePrefix(String player, boolean isALL) {
		if (isALL) {
			HashMap<String, String> map = Vars.prefix.getStrings();
			for (Entry<String, String> ele : map.entrySet()) {
				if (ele.getKey().replace('$', '＄').split("＄")[0]
						.equalsIgnoreCase(player))
					map.remove(ele.getKey());
			}
		} else {
			Vars.prefix.getStrings().remove(player + "$default");
		}
		if (autoSave)
			savePrefix();
	}

	public static void removePrefix(String prefix) {
		HashMap<String, String> map = Vars.prefix.getStrings();
		for (Entry<String, String> ele : map.entrySet()) {
			if (ele.getValue().equalsIgnoreCase(prefix))
				map.remove(ele.getKey());
		}
		if (autoSave)
			savePrefix();
	}

	public static List<String> getPrefixs(String player) {
		List<String> prefixs = new ArrayList<String>();
		HashMap<String, String> map = Vars.prefix.getStrings();
		for (Entry<String, String> ele : map.entrySet()) {
			if (ele.getKey().replace('$', '＄').split("＄")[0]
					.equalsIgnoreCase(player))
				prefixs.add(ele.getValue());
		}
		return prefixs;
	}

	public static Map<String, String> getPrefixMap(String player) {
		Map<String, String> prefixs = new HashMap<String, String>();
		HashMap<String, String> map = Vars.prefix.getStrings();
		for (Entry<String, String> ele : map.entrySet()) {
			if (ele.getKey().replace('$', '＄').split("＄")[0]
					.equalsIgnoreCase(player))
				prefixs.put(ele.getKey().replace('$', '＄').split("＄")[1],
						ele.getValue());
		}
		return prefixs;
	}

	public static String getPrefix(String player) {
		String p = "";
		for (String preifx : getPrefixs(player)) {
			p += preifx;
		}
		return p;
	}

	public static void savePrefix() {
		Vars.pigData.set("prefix", Vars.prefix).saveToFile(Vars.dataFile);
	}
}
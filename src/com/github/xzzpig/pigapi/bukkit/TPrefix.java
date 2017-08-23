package com.github.xzzpig.pigapi.bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.xzzpig.pigapi.plugin.Vars;
import com.github.xzzpig.pigutils.data.PigData;

public class TPrefix {
	public static boolean autoSave = true;

	public static String getPrefix(String player) {
		String p = "";
		for (String preifx : getPrefixs(player)) {
			p += preifx;
		}
		return p;
	}

	public static Map<String, String> getPrefixMap(String player) {
		Map<String, String> prefixs = new HashMap<String, String>();
		Set<Entry<String, Object>> entrys = Vars.prefix.getSub(player).getData().entrySet();
		for (Entry<String, Object> entry : entrys) {
			try {
				prefixs.put(entry.getKey(), (String) entry.getValue());
			} catch (Exception e) {
			}
		}
		return prefixs;
	}

	public static List<String> getPrefixs(String player) {
		List<String> prefixs = new ArrayList<String>();
		Set<Entry<String, Object>> entrys = Vars.prefix.getSub(player).getData().entrySet();
		for (Entry<String, Object> entry : entrys) {
			prefixs.add(entry.getValue() + "");
		}
		return prefixs;
	}

	public static void removePrefix(String prefix) {
		List<PigData> players = Vars.prefix.getSubList(null);
		for (PigData ids : players) {
			Set<Entry<String, Object>> entrys = ids.getData().entrySet();
			for (Entry<String, Object> entry : entrys) {
				if ((entry.getValue() + "").equalsIgnoreCase(prefix))
					ids.getData().remove(entry.getKey());
			}
		}
		if (autoSave)
			savePrefix();
	}

	public static void removePrefix(String player, boolean isALL) {
		if (isALL) {
			// HashMap<String, String> map = Vars.prefix.getStrings();
			// for (Entry<String, String> ele : map.entrySet()) {
			// if (ele.getKey().replace('$', '锛�').split("锛�")[0]
			// .equalsIgnoreCase(player))
			// map.remove(ele.getKey());
			// }
			Vars.prefix.remove(player);
		} else {
			Vars.prefix.remove(player + ".default");
		}
		if (autoSave)
			savePrefix();
	}

	public static void removePrefix(String player, String id) {
		Vars.prefix.remove(player + "." + id);
		if (autoSave)
			savePrefix();
	}

	public static void savePrefix() {
		Vars.pigData.set("prefix", Vars.prefix).saveToFile(Vars.dataFile);
	}

	public static void setPrefix(String player, String prefix) {
		setPrefix(player, prefix, "default");
	}

	public static void setPrefix(String player, String prefix, String id) {
		if (id == null || id.equalsIgnoreCase(""))
			id = "default";
		Vars.prefix.set(player + "." + id, prefix);
		if (autoSave)
			savePrefix();
	}
}
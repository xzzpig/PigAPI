
package com.github.xzzpig.pigapi.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TPlayer {
	public static int ExpToLevel(int exp) {
		int level = 0;
		int tlevel = 0;
		if (exp <= 352) {
			for (tlevel = 0; tlevel <= 16; tlevel++) {
				if (tlevel * tlevel + 6 * tlevel > exp) {
					level = tlevel - 1;
					break;
				}
			}
		} else if (exp <= 1507) {
			for (tlevel = 17; tlevel <= 31; tlevel++) {
				if (2.5 * tlevel * tlevel - 40.5 * tlevel + 360 > exp) {
					level = tlevel - 1;
					break;
				}
			}
		} else {
			for (tlevel = 32;; tlevel++) {
				if (4.5 * tlevel * tlevel - 162.5 * tlevel + 2220 > exp) {
					level = tlevel - 1;
					break;
				}
			}
		}
		return level;
	}

	public static int toLevelExp(int level){
		if(level<=16){
			return level*2+7;
		}
		else if(level>16&&level<=31){
			return level*5-38;
		}
		else{
			return level*9-158;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static Player[] getAllPlayers() {
		return Bukkit.getOnlinePlayers();
	}
}

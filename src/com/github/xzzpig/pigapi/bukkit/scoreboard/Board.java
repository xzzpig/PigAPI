package com.github.xzzpig.pigapi.bukkit.scoreboard;

import java.util.Collection;

import org.bukkit.entity.Player;

public abstract class Board {
	public void startDisplay(Collection<Player> players) {
		for (Player p : players)
			this.startDisplay(p);
	}

	public abstract void startDisplay(Player p);

	public abstract void stopDisplay(Player p);
}

package com.github.xzzpig.pigapi.bukkit.scoreboard;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class SimpleRankedPage implements BoardPage {
	private HashMap<String, Integer> content;
	private String title;

	public SimpleRankedPage(String title, HashMap<String, Integer> content) {
		this.title = title;
		this.content = content;
	}

	@Override
	public void update(Player p) {
		ScoreboardUtil.rankedSidebarDisplay(p, title, content);
	}
}
package com.github.xzzpig.pigapi.bukkit.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class PagedBoard extends AutomaticBoard {
	private int count, currentPageId;
	private HashMap<BoardPage, Integer> pages;

	public PagedBoard() {
		super(1);
		this.pages = new HashMap<BoardPage, Integer>();
		this.count = 0;
	}

	public void addPage(BoardPage page, int ticks) {
		this.pages.put(page, ticks);
	}

	public BoardPage getPage() {
		return new ArrayList<BoardPage>(pages.keySet()).get(currentPageId);
	}

	public void removePage(BoardPage page) {
		this.pages.remove(page);
	}

	@Override
	public void run() {
		super.run();
		if (++this.count >= pages.get(getPage())) {
			this.count = 0;
			this.currentPageId++;

			if (this.currentPageId >= this.pages.size())
				this.currentPageId = 0;
		}
	}

	@Override
	public void update(Player p) {
		getPage().update(p);
	}
}
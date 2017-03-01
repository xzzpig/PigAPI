package com.github.xzzpig.pigapi.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.xzzpig.pigapi.bukkit.TMessage;
import com.github.xzzpig.pigapi.bukkit.TPlayer;
import com.github.xzzpig.pigapi.bukkit.TStringMatcher;

public class ChatManager implements Listener {
	public static ChatManager self = new ChatManager();

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		if (!Vars.enable_chatmanager)
			return;
		String[] formats = TStringMatcher.buildStr(Vars.chatformat, event.getPlayer(), false)
				.replaceAll("</message/>", "^^&r" + event.getMessage() + "^^").split("\n");
		for (int i = 0; i < formats.length; i++) {
			TMessage message = TMessage.getBy(formats[i], false);
			for (Player player : TPlayer.getAllPlayers()) {
				message.send(player);
			}
		}
		event.setCancelled(true);
		// event.setFormat(formats[formats.length - 1]);
	}
}
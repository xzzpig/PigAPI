package com.github.xzzpig.pigapi.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.github.xzzpig.pigapi.Debuger;
import com.github.xzzpig.pigapi.bukkit.TPlayer;
import com.github.xzzpig.pigapi.bukkit.TStringMatcher;

public class ChatManager implements Listener {
	public static ChatManager self = new ChatManager();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(!Vars.enable_chatmanager)
			return;
//		Debuger.setIsDebug(ChatManager.class, true);
//		Debuger.print(event.getFormat());
		String[] formats = TStringMatcher.buildStr(Vars.chatformat,event.getPlayer(),false).replaceAll("</message/>",event.getMessage()).split("\n");
		for (int i = 0; i < formats.length-1; i++) {
			for (Player player:TPlayer.getAllPlayers()) {
				player.sendMessage(formats[i]);
			}
		}
		Debuger.debug = true;
		event.setFormat(formats[formats.length-1]);
	}
}
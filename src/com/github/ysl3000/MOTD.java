package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MOTD implements Listener {

	private SmartServerTool plugin;
	private String firstjoin;
	private String joinmessage;
	private String leftmessage;

	public MOTD(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;
		firstjoin = this.plugin.getConfig().getString("firstjoin");
		joinmessage = this.plugin.getConfig().getString("message");
		leftmessage = this.plugin.getConfig().getString("leftmessage");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		joinmessage = null;
		joinmessage = this.plugin.getConfig().getString("message");
		Player player = event.getPlayer();
		joinmessage = joinmessage.replace("%user", ChatColor.GOLD + player.getName()+ ChatColor.WHITE);
		joinmessage = joinmessage.replace("%server", ChatColor.GREEN +Bukkit.getServerName());
		

		if (!player.hasPlayedBefore()) {

			event.setJoinMessage(joinmessage.concat(" " + firstjoin));
		} else {

			event.setJoinMessage(joinmessage);
		}

		Prefix.Pfix(player);
	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {

		leftmessage = null;
		leftmessage = this.plugin.getConfig().getString("leftmessage");
		
		Player player = event.getPlayer();
		leftmessage = leftmessage.replace("%user", ChatColor.GOLD + player.getName()+ ChatColor.WHITE);
		leftmessage = leftmessage.replace("%server",ChatColor.GREEN + Bukkit.getServerName());

		event.setQuitMessage(leftmessage);
	}

}

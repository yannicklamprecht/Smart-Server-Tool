package com.github.ysl3000;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {

	public static SmartServerTool plugin;
	HashMap<String, Integer> login = new HashMap<String, Integer>();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		
		Player player = event.getPlayer();
		
		
		if(!player.hasPlayedBefore()){
			event.setJoinMessage("Welcome  " + ChatColor.GOLD + player.getName()
					+ ChatColor.WHITE + "  to  " + ChatColor.GREEN
					+ Bukkit.getServerName()+ChatColor.WHITE+"!\n"+player.getName()+ " is playing the first time!");
		}else{
			event.setJoinMessage("Welcome  " + ChatColor.GOLD + player.getName()
					+ ChatColor.WHITE + "  to  " + ChatColor.GREEN
					+ Bukkit.getServerName());
		}
		

	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		event.setQuitMessage(ChatColor.GOLD + player.getName()
				+ ChatColor.WHITE + " has left " + ChatColor.GREEN
				+ Bukkit.getServerName());

	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {

		Player player = event.getPlayer();
		
		Spawnarea.respawn(player,player.getWorld().getName());
	}

}

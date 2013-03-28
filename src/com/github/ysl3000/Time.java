package com.github.ysl3000;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;


public class Time {

	public static boolean setTime(Player player, String command) throws Exception {

		if (SmartServerTool.getCommands().getTd(command) && SmartServerTool.getPermission().hasSetTime(player)) {

			player.getWorld().setTime(0);
			player.sendMessage("Time set to " + ChatColor.GOLD + "Day");

		} else if (SmartServerTool.getCommands().getTn(command)
				&& SmartServerTool.getPermission().hasSetTime(player)) {

			player.getWorld().setTime(18000);
			player.sendMessage("Time set to " + ChatColor.GOLD + "Night");

		} else if (SmartServerTool.getCommands().getT(command)
				&& SmartServerTool.getPermission().hasGetTime(player)) {

			player.sendMessage("Current Time " + ChatColor.GOLD
					+ SmartServerTool.getDateTime().getRealTime("HH:mm", player.getWorld().getTime()));
		}
		return false;
	}

}

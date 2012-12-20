package com.github.ysl3000;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

public class Time {

	public static boolean setTime(Player player, String command) throws Exception {

		if (Commands.getTd(command) && Permission.hasSetTime(player)) {

			player.getWorld().setTime(0);
			player.sendMessage("Time set to " + ChatColor.GOLD + "Day");

		} else if (Commands.getTn(command)
				&& Permission.hasSetTime(player)) {

			player.getWorld().setTime(18000);
			player.sendMessage("Time set to " + ChatColor.GOLD + "Night");

		} else if (Commands.getT(command)
				&& Permission.hasGetTime(player)) {

			player.sendMessage("Current Time " + ChatColor.GOLD
					+ DateTime.getRealTime("HH:mm", player.getWorld().getTime()));
		}
		return false;
	}

}

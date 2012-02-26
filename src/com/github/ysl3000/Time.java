package com.github.ysl3000;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

public class Time {

	public static void setTime(Player player, String command) throws Exception {

		if (command.equalsIgnoreCase("td") && player.hasPermission("MyOwn.td")) {

			player.getWorld().setTime(0);
			player.sendMessage("Time set to" + ChatColor.GOLD + "Day");

		} else if (command.equalsIgnoreCase("tn")
				&& player.hasPermission("MyOwn.tn")) {

			player.getWorld().setTime(18000);
			player.sendMessage("Time set to" + ChatColor.GOLD + "Night");

		} else if (command.equalsIgnoreCase("t")
				&& player.hasPermission("MyOwn.t")) {

			player.sendMessage("Current Time " + ChatColor.GOLD
					+ player.getWorld().getTime());
		}
	}

}

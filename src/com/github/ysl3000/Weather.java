package com.github.ysl3000;

import org.bukkit.ChatColor;

import org.bukkit.entity.Player;

public class Weather {

	
	public static void toggleGm(Player player, String command) throws Exception {

		if (command.equalsIgnoreCase("sun")
				&& player.hasPermission("MyOwn.sun")) {

			player.getWorld().setWeatherDuration(1);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Sun");

		} else if (command.equalsIgnoreCase("storm")
				&& player.hasPermission("MyOwn.storm")) {

			player.getWorld().setWeatherDuration(0);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");

		} else if (command.equalsIgnoreCase("wg")
				&& player.hasPermission("MyOwn.wg")) {

			player.sendMessage("Current Weather " + ChatColor.GOLD
					+ player.getWorld().getWeatherDuration());

			
		}

	}
}

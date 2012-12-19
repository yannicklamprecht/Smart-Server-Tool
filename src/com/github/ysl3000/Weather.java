package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Weather {

	
	private static World_Weather welt = new World_Weather("world", "default");

	public static boolean changeWeather(Player player, String command) throws Exception {

		final World world = player.getWorld();
		
		if (Commands.getSun(command)
				&& Permission.hasWeather(player)) {

			world.setThundering(false);
			world.setStorm(false);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Sun");
			welt.setName(player.getWorld().getName());
			welt.setStatus("Sunny");


		} else if (Commands.getStorm(command)
				&& Permission.hasWeather(player)) {

			world.setStorm(true);
			world.setThundering(true);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");
			
			welt.setName(player.getWorld().getName());
			welt.setStatus("Stormy");


		} else if (Commands.getWeather(command)
				&& Permission.hasGetWeather(player)) {

			player.sendMessage("Current Weather in "+welt.getName() +" is " + ChatColor.GOLD
					+ welt.getinfo() );

		}
		return false;

	}

	

}

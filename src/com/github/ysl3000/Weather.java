package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Weather {

	
	private static Welt welt = new Welt("world", "default");

	public static void changeWeather(Player player, String command) throws Exception {

		final World world = player.getWorld();
		
		if (command.equalsIgnoreCase("sun")
				&& player.hasPermission("MyOwn.sun")) {

			world.setThundering(false);
			world.setStorm(false);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Sun");
			welt.setName(player.getWorld().getName());
			welt.setStatus("Sunny");


		} else if (command.equalsIgnoreCase("storm")
				&& player.hasPermission("MyOwn.storm")) {

			world.setStorm(true);
			world.setThundering(true);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");
			
			welt.setName(player.getWorld().getName());
			welt.setStatus("Stormy");


		} else if (command.equalsIgnoreCase("wg")
				&& player.hasPermission("MyOwn.wg")) {

			player.sendMessage("Current Weather in "+welt.getName() +" is " + ChatColor.GOLD
					+ welt.getinfo() );

		}

	}

	

}

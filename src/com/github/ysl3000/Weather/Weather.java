package com.github.ysl3000.Weather;

import org.bukkit.ChatColor;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.ysl3000.SmartServerTool;

public class Weather {

	private static World_Weather welt = new World_Weather("world", "default");

	public static boolean changeWeather(Player player, String command)
			throws Exception {

		final World world = player.getWorld();

		if (SmartServerTool.getCommands().getSun(command)
				&& SmartServerTool.getPermission().hasWeather(player)) {

			world.setThundering(false);
			world.setStorm(false);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Sun");
			welt.setName(player.getWorld().getName());
			welt.setStatus("Sunny");

		} else if (SmartServerTool.getCommands().getStorm(command)
				&& SmartServerTool.getPermission().hasWeather(player)) {

			world.setStorm(true);
			world.setThundering(true);
			player.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");

			welt.setName(player.getWorld().getName());
			welt.setStatus("Stormy");

		} else if (SmartServerTool.getCommands().getWeather(command)
				&& SmartServerTool.getPermission().hasGetWeather(player)) {

			player.sendMessage("Current Weather in " + welt.getName() + " is "
					+ ChatColor.GOLD + welt.getinfo());

		}
		return false;

	}

	public static void runPlayerWeather(CommandSender sender, String command,
			String[] args, Command cmd) {
		World_Weather type = new World_Weather("Player", "Default");
		type.setName(((Player) sender).getDisplayName());
		setPlayerWeather(((Player) sender), command, type);
		resetForPlayer(((Player) sender), command, type);
	}

	private static World_Weather resetForPlayer(Player player, String command,
			World_Weather type) {
		if (SmartServerTool.getCommands().getPreset(command)) {
			player.resetPlayerWeather();
			type.setStatus("Reset to ServerWeather");

		}
		return type;
	}

	private static World_Weather setPlayerWeather(Player player,
			String command, World_Weather type) {
		if (SmartServerTool.getCommands().getPsun(command)) {
			player.setPlayerWeather(WeatherType.CLEAR);
			type.setStatus("Sun");
		} else if (SmartServerTool.getCommands().getPrain(command)) {
			player.setPlayerWeather(WeatherType.DOWNFALL);
			type.setStatus("Rain");
		}

		return type;
	}

}

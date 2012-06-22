package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnArea {

	public static void spawn(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		if (command.equalsIgnoreCase("setsp")
				&& sender.hasPermission("sst.setsp")) {

			Player player = (Player) sender;

			Bukkit.getWorld(player.getWorld().getName()).setSpawnLocation(
					player.getLocation().getBlockX(),
					player.getLocation().getBlockY(),
					player.getLocation().getBlockZ());

			player.sendMessage("Spawn of " + player.getWorld().getName()
					+ " set");

		} else if (command.equalsIgnoreCase("spawn")
				&& sender.hasPermission("sst.spawn")) {

			Player player = (Player) sender;

			Location lc = Bukkit.getWorld(player.getWorld().getName())
					.getSpawnLocation();

			player.teleport(lc);

			player.sendMessage("Teleported to Spawn of world " + ChatColor.GOLD
					+ player.getWorld().getName());

		}

	}

	public static void tospawn(Player player) throws Exception {

		player.teleport(player.getWorld().getSpawnLocation());

	}
}

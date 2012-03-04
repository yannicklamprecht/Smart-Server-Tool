package com.github.ysl3000;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Spawnarea {
	private static Location spawn;
	

	public static void setspawn(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		if (command.equalsIgnoreCase("/setspawn")
				&& sender.hasPermission("sst.setspawn")) {

			Player player = (Player) sender;

			
			spawn.setWorld(player.getWorld());
			spawn.setX(player.getLocation().getX());
			spawn.setY(player.getLocation().getY());
			spawn.setZ(player.getLocation().getZ());
			spawn.setYaw(player.getLocation().getYaw());
			spawn.setPitch(player.getLocation().getPitch());
			player.sendMessage("Spawn set in " + player.getWorld().getName());

		}else{
			
		}
	}

	public static void teleportspawn(Player player, String command) throws Exception {

		if (command.equalsIgnoreCase("/spawn")
				&& player.hasPermission("sst.spawn")) {

			player.teleport(spawn.getWorld().getSpawnLocation());
			player.sendMessage("Teleported to Spawn");
		}else{
			
		}

	}
}

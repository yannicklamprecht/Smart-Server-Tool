package com.github.ysl3000;

import java.util.HashMap;


import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Teleport {

	static HashMap<Player, Location> lastL = new HashMap<Player, Location>();

	public static boolean tp(Player player, String command, String[] args,
			Command cmd) throws Exception {

		tpm(player, command, args, cmd);

		home(player, command, args, cmd);

		return true;

	}

	public static void tpm(Player player, String command, String[] args,
			Command cmd) throws Exception {
		if (cmd.getName().equalsIgnoreCase("tp")
				&& player.hasPermission("sst.tpt")) {

			if (args.length == 0) {
				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				lastL.put(player, player.getLocation());

				player.teleport(target);
				player.sendMessage("Teleported to " + target.getDisplayName());
				target.sendMessage(player.getName()
						+ " teleported to you");
			}

		} else if (cmd.getName().equalsIgnoreCase("tpo")
				&& player.hasPermission("sst.tpo")) {

			if (args.length == 0) {

				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				lastL.put(target, target.getLocation());
				target.teleport(player);

				player.sendMessage("You Teleported " + target.getDisplayName()
						+ " to you");
				target.sendMessage(player.getDisplayName() + " teleported "
						+ target.getDisplayName() + " to "
						+ player.getDisplayName());
			}

		} else if (cmd.getName().equalsIgnoreCase("switch")
				&& player.hasPermission("sst.switch")) {

			if (args.length == 0) {
				player.sendMessage("Not enough arguments");
			} else if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				lastL.put(target, target.getLocation());
				lastL.put(player, player.getLocation());

				player.teleport(lastL.get(target));
				target.teleport(lastL.get(player));

				player.sendMessage("You changed position with "
						+ target.getDisplayName());
				target.sendMessage(player.getDisplayName()
						+ " changed position with you. Changed by "
						+ player.getDisplayName());

			} else {
				player.sendMessage("to many arguments");
			}
		} else if (cmd.getName().equalsIgnoreCase("back")) {

			lastL = PlayerListener.getlocation();
			player.teleport(lastL.get(player));

		}
	}

	public static void home(Player player, String command, String[] args,
			Command cmd) throws Exception {

		if (cmd.getName().equalsIgnoreCase("home")
				&& player.hasPermission("sst.home")) {
			if (player.getBedSpawnLocation() != null) {

				if (args.length == 0) {

					player.teleport(player.getBedSpawnLocation());
				} else if (args.length == 1
						&& player.hasPermission("sst.homeo")) {

					Player target = player.getServer().getPlayer(args[0]);
					player.teleport(target.getLocation());
				}
			} else {
				player.sendMessage("No home set");
			}

		} else if (cmd.getName().equalsIgnoreCase("seth")
				&& player.hasPermission("sst.seth")) {
			player.setBedSpawnLocation(player.getLocation());

		}
	}

}

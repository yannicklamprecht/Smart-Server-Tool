package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Teleport {

	public static boolean tp(Player player, String command, String[] args,
			Command cmd) throws Exception {

		if (cmd.getName().equalsIgnoreCase("/tp")
				&& player.hasPermission("sst.tp")) {

			if (args.length == 0) {
				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				player.teleport(target);
				player.sendMessage("Teleported to " + target.getName());
				target.sendMessage(player.getName() + " teleported to you");
			}

		} else if (cmd.getName().equalsIgnoreCase("/tpo")
				&& player.hasPermission("sst.tpo")) {

			if (args.length == 0) {

				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);
				target.teleport(player);

				player.sendMessage("You Teleported " + target.getName() + " to you");
				target.sendMessage(player + " teleported " + target + " to "
						+ player);
			}

		} else if (cmd.getName().equalsIgnoreCase("/switch")
				&& player.hasPermission("sst.switch")) {

			if (args.length == 0) {
				player.sendMessage("Not enough arguments");
			} else if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);
				Player temp = player.getPlayer();

				player.teleport(target);
				target.teleport(temp);

				player.sendMessage("You changed position with " + target.getName());
				target.sendMessage(player
						+ " changed position with you. Changed by " + player.getName());

			} else {
				player.sendMessage("to many arguments");
			}
		}
		return true;

	}

}

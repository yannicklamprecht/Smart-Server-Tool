package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Gm {

	public static boolean toggleGm(Player player, String command, String[] args,
			Command cmd) throws Exception {

		if (command.equalsIgnoreCase("gmc")
				&& player.hasPermission("sst.gmc")) {

			if (args.length == 0) {

				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage("Enter " + ChatColor.GOLD + "Creative mode");
			}
			if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				if (target == null) {

					player.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.CREATIVE);

				target.sendMessage(ChatColor.GREEN + "You Gamemode now is "
						+ target.getGameMode() + ", set by "
						+ ChatColor.DARK_PURPLE + player.getName());

				player.sendMessage(ChatColor.GREEN + "You set "
						+ target.getGameMode() + " for "
						+ ChatColor.DARK_PURPLE +

						target.getName());
			}

		} else if (command.equalsIgnoreCase("gms")
				&& player.hasPermission("sst.gms")) {

			if (args.length == 0) {

				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("Enter " + ChatColor.GOLD
						+ player.getGameMode());
			}
			if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				if (target == null) {

					player.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.SURVIVAL);

				target.sendMessage(ChatColor.GREEN + "You entered"
						+ target.getGameMode() + " set by " + ChatColor.DARK_PURPLE
						+ player.getName());

				player.sendMessage(ChatColor.GREEN + "Gamemode set to  "
						+ target.getGameMode() + ChatColor.DARK_PURPLE +

						target.getName());
			}

		} else if (command.equalsIgnoreCase("gm")
				&& player.hasPermission("sst.gm")) {

			player.sendMessage("Current GameMode " + ChatColor.GOLD
					+ player.getGameMode());

		}
		return true;

	}

}

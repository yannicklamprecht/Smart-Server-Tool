package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Gm {

	public static void toggleGm(Player player, String command) throws Exception {

		if (command.equalsIgnoreCase("gmc")
				&& player.hasPermission("MyOwn.gmc")) {

			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage("Enter " + ChatColor.GOLD + "Creative mode");

		} else if (command.equalsIgnoreCase("gms")
				&& player.hasPermission("MyOwn.gms")) {

			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage("Enter " + ChatColor.GOLD + "Survival mode");

		} else if (command.equalsIgnoreCase("gm")
				&& player.hasPermission("MyOwn.gm")) {

			player.sendMessage("Current GameMode " + ChatColor.GOLD
					+ player.getGameMode());

		}

	}

}

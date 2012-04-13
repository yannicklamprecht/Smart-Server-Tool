package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideP {

	public static SmartServerTool plugin;

	public static boolean hide(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		Player player = (Player) sender;
		Player[] playerlist = Bukkit.getOnlinePlayers();

		if ((command.equalsIgnoreCase("hide"))
				&& (sender.hasPermission("sst.visible"))) {

			player.sendMessage("You were hidden");
			player.getPlayerListName();

			

				player.setPlayerListName(ChatColor.BLUE + "Herobrine"
						+ ChatColor.WHITE);
				player.setDisplayName(ChatColor.BLUE + "Herobrine"
						+ ChatColor.WHITE);
			
			
			player.hidePlayer(player);

			for (int i = 0; i < playerlist.length; i++) {

				if (!playerlist[i].hasPermission("sst.cansee")) {
					playerlist[i].hidePlayer(player);
				} else {
					playerlist[i].showPlayer(player);
				}

			}

		} else if ((command.equalsIgnoreCase("show"))
				&& (player.hasPermission("sst.visible"))) {

			Prefix.Pfix(player);

			player.sendMessage("You you are now shown");
			for (int i = 0; i < playerlist.length; i++) {

				playerlist[i].showPlayer(player);

			}

		}

		return true;

	}
}

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

		if (sender instanceof Player) {

			if ((command.equalsIgnoreCase("hide"))
					&& (sender.hasPermission("sst.visible"))) {

				Player player = (Player) sender;

				player.sendMessage("You were hidden");
				player.getPlayerListName();
				player.setPlayerListName(ChatColor.BLUE + "[H]"
						+ ChatColor.RESET + player.getDisplayName());
				player.setDisplayName(ChatColor.BLUE + "[H]" + ChatColor.RESET
						+ player.getDisplayName() + ChatColor.WHITE);

				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!p.hasPermission("sst.cansee")) {
						p.hidePlayer(player);
					}

				}

			} else if ((command.equalsIgnoreCase("show"))
					&& (sender.hasPermission("sst.visible"))) {

				
				Prefix.Pfix((Player)sender);
				((Player)sender).sendMessage("You you are now shown");

				for (Player p : Bukkit.getOnlinePlayers()) {

					p.showPlayer((Player)sender);
				}

			}
		}

		return true;

	}
}

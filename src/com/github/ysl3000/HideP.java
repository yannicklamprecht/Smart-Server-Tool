
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

				if (sender instanceof Player) {

					Player player = (Player) sender;

					
					player.sendMessage("You were hidden");
					player.getPlayerListName();

					
					
					for(Player p:Bukkit.getServer().getOnlinePlayers()){
						
						if(p.getDisplayName().equalsIgnoreCase("Herobrine")){
							
							
						}
						
						player.setPlayerListName(ChatColor.BLUE + "Herobrine"
								+ ChatColor.WHITE);
						player.setDisplayName(ChatColor.BLUE + "Herobrine"
								+ ChatColor.WHITE);
						
					}
							
					
					

					for (Player p : Bukkit.getOnlinePlayers()) {

						if (!p.hasPermission("sst.cansee")) {
							p.hidePlayer(player);
						} else {
							p.showPlayer(player);
						}

					}
				} else {
					sender.sendMessage("Only Player can perform this command");
				}

			} else if ((command.equalsIgnoreCase("show"))
					&& (sender.hasPermission("sst.visible"))) {

				if (sender instanceof Player) {

					Player player = (Player) sender;
					Prefix.Pfix(player);
					player.sendMessage("You you are now shown");
					for (Player p : Bukkit.getOnlinePlayers()) {

						p.showPlayer(player);

					}
				} else {
					sender.sendMessage("Only Player can perform this command");
				}

			}
		}

		return true;

	}
}

package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Health {

	public static boolean kill(CommandSender sender, String commandLabel,
			String[] split, Command cmd) throws Exception {

		Player player = (Player) sender;
		if (commandLabel.equalsIgnoreCase("km")
				&& sender.hasPermission("sst.km")) {
			
			if(sender instanceof Player){

			player.setHealth(0);
			}else{
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}
		} else if (commandLabel.equalsIgnoreCase("kill")
				&& sender.hasPermission("sst.kill")) {

			if (split.length == 0) {
				
				

				player.sendMessage("Wrong Input");
			} else if (split.length == 1) {

				Player target = sender.getServer().getPlayer(split[0]);

				target.setHealth(0);

			}

		} else if (cmd.getName().equalsIgnoreCase("heal")
				&& sender.hasPermission("sst.heal")) {

			if (split.length == 0) {
				
				if(sender instanceof Player){
					player.setHealth(20);

					player.setFoodLevel(20);

					player.sendMessage(ChatColor.GREEN + "Healed!");
				}else{
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

				

			}else if (split.length == 1) {

				Player target = sender.getServer().getPlayer(split[0]);

				if (target == null) {

					sender.sendMessage("PLAYER " + target + " isn't found");

				}

				target.setHealth(20);

				target.setFoodLevel(20);

				target.sendMessage(ChatColor.GREEN + "You have been healed by "
						+ ChatColor.DARK_PURPLE + sender.getName());

				sender.sendMessage(ChatColor.GREEN + "You healed "
						+ ChatColor.DARK_PURPLE +

						target.getName());

			}

		}
		return false;
	}
}

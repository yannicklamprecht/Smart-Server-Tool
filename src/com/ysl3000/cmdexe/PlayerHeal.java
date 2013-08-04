package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerHeal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;
		if(args.length != 1){
			player.sendMessage("You need one argument");
			return true;
		}
		Player target = sender.getServer().getPlayer(args[0]);

		if (player.hasPermission(cmd.getPermission())) {

			if (target == null) {

				sender.sendMessage("Player not found");
				return true;

			}
			target.setHealth(20.0);
			target.setFoodLevel(20);
			target.sendMessage(ChatColor.GREEN + "You have been healed by "
					+ ChatColor.DARK_PURPLE + player.getDisplayName());

			player.sendMessage(ChatColor.GREEN + "You healed "
					+ ChatColor.DARK_PURPLE + target.getName());
		}

		return true;
	}

}

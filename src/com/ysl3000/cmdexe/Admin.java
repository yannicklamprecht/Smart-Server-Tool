package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Admin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (args.length == 0) {
			if (!player.isOp() && player.hasPermission(cmd.getPermission())) {

				player.setOp(true);

				player.sendMessage((ChatColor.GREEN + "Op enabled"));

			} else if (player.isOp() && player.hasPermission(cmd.getPermission())) {

				player.setOp(false);

				player.sendMessage((ChatColor.RED + "Op disabled"));
			}
		} else if (args.length == 1) {
			player.sendMessage("please use /deop <player>");
		}

		return true;
	}

}

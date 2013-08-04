package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerLookupIpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission(cmd.getPermission())) {
			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {

				player.sendMessage("PLAYER " + target + " isn't found");
				return true;
			}
			player.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
					+ ChatColor.WHITE + " is " + ChatColor.YELLOW
					+ target.getAddress());

		}
		return true;
	}

}

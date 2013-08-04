package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.permissions.Permissions;

public class CheckCurrentGamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission(cmd.getPermission())) {

			if (args.length == 0) {

				player.sendMessage("Current GameMode " + ChatColor.GOLD
						+ player.getGameMode());

			} else if (args.length == 1) {

				if (player.hasPermission(Permissions.gmlookupOther)) {

					Player target = Bukkit.getPlayer(args[0]);
					sender.sendMessage("Current GameMode of " + ChatColor.GOLD
							+ target.getDisplayName() + " "
							+ target.getGameMode());
				} else {

					sender.sendMessage("You aren't allowed to lookup others gamemode");
				}
			}

		}

		return true;
	}

}

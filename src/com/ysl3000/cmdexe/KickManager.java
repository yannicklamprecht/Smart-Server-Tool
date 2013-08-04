package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class KickManager implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission())) {

			player.sendMessage(SmartServerTool.noperms);
			return true;
		}

		if (args.length == 0) {

			player.sendMessage("Usage: /kick <name|-a>");
		} else if (args.length == 1) {

			if (args[0].equalsIgnoreCase("-a")) {

				for (Player p : Bukkit.getOnlinePlayers()) {
					if (!p.getName().equalsIgnoreCase(player.getName())) {
						p.kickPlayer(player.getName() + " has kicked you");
					}

				}
			} else {
				Bukkit.getPlayer(args[0]).kickPlayer(
						player.getName() + " has kicked you");
			}

		} else

		if (args.length >= 2 && args.length <= 10) {

			if (args[0].equalsIgnoreCase("-a")) {

				for (Player p : Bukkit.getOnlinePlayers()) {

					if (!p.getName().equalsIgnoreCase(player.getName())) {
						p.kickPlayer(args[1] + " " + args[2] + " " + args[3]
								+ " " + args[4] + " " + args[5] + " " + args[6]);
					}

				}
			} else {

				Bukkit.getPlayer(args[0]).kickPlayer(
						args[1] + " " + args[2] + " " + args[3] + " " + args[4]
								+ " " + args[5] + " " + args[6]);
				Bukkit.broadcastMessage(((Player) Bukkit
						.getOfflinePlayer(args[0])).getDisplayName()
						+ " is kicked because "
						+ args[1]
						+ " "
						+ args[2]
						+ " "
						+ args[3]
						+ " "
						+ args[4]
						+ " "
						+ args[5]
						+ " "
						+ args[6]);
			}
		}

		return true;
	}
}

package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.Utility;

public class PlayerLastSeen implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String comandLabel, String[] args) {

		Player player = (Player) sender;

		if (args.length == 0) {
			player.sendMessage("Use /seen <player>");
		} else if (args.length == 1) {
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);

			if (op.hasPlayedBefore()) {
				player.sendMessage(ChatColor.GREEN
						+ "Player was first seen on: "
						+ ChatColor.GOLD
						+ Utility.getTime(op.getFirstPlayed(),
								"MMM dd yyyy HH:mm")
						+ "\n"
						+ ChatColor.GREEN
						+ "and last seen on: "
						+ ChatColor.GOLD
						+ Utility.getTime(op.getLastPlayed(),
								"MMM dd yyyy HH:mm"));
			} else {
				player.sendMessage(ChatColor.GREEN + "Player " + ChatColor.GOLD
						+ op.getName() + ChatColor.GREEN
						+ " never seen before!");
			}
		} else if (args.length > 1) {
			player.sendMessage(ChatColor.RED + "To much arguments");
		}

		return true;
	}

}

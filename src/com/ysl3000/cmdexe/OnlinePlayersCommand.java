package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.permissions.Utility;

public class OnlinePlayersCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		((Player) sender).sendMessage(ChatColor.GRAY + "Online ("
				+ Bukkit.getServer().getOnlinePlayers().length + "/"
				+ Bukkit.getMaxPlayers() + "): " + Utility.listPlayers());

		return true;
	}

}

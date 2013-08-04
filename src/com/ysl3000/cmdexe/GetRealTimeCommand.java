package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.permissions.Utility;

public class GetRealTimeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		player.sendMessage("Current Time "
				+ ChatColor.GOLD
				+ Utility.getTime(player.getWorld().getTime(),"HH:mm"));

		return true;
	}

	
}

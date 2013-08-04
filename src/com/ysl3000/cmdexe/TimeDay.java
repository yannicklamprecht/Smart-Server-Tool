package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeDay implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {

		Player player = (Player)sender;
		
		if(player.hasPermission(cmd.getPermission())){
		player.getWorld().setTime(0);
		player.sendMessage("Time set to " + ChatColor.GOLD + "Day");
		}
		
		return true;
	}

}

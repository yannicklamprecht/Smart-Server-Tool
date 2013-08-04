package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherStorm implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		
		Player p = (Player)sender;
		if(p.hasPermission(cmd.getPermission())){
		p.getWorld().setStorm(true);
		p.getWorld().setThundering(true);
		p.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");
		}
		return true;
	}

}

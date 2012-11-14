package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Inviter {

	
	public static void invite(CommandSender sender, String commandLabel, String[] args, Command cmd){
		
		if(commandLabel.equalsIgnoreCase("invite") && sender.hasPermission("sst.invite")){
			
			if(args.length == 0){
				sender.sendMessage("Use /invite <player>  to invite your friends");
			}else if(args.length == 1){
				Bukkit.getServer().getWhitelistedPlayers().add(Bukkit.getOfflinePlayer(args[0]));
				sender.sendMessage("You've invited "+ ((Player)Bukkit.getOfflinePlayer(args[0])).getName());
				SmartServerTool.plugin.getInviteConfig().set(sender+"."+((Player)Bukkit.getOfflinePlayer(args[0])).getName(), true);  
			}
		}else if(commandLabel.equalsIgnoreCase("invite") && !sender.hasPermission("sst.invite")){
			sender.sendMessage("sst.invite");
		}
	}
}

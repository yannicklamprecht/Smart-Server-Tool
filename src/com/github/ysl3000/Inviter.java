package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Inviter {

	
	public static void invite(CommandSender sender, String commandLabel, String[] args, Command cmd){
		
		if(SmartServerTool.getCommands().getInvite(commandLabel) && SmartServerTool.getPermission().hasInvite((Player)sender)){
			
			if(args.length == 0){
				sender.sendMessage("Use /invite <player>  to invite your friends! Please write the full name!");
			}else if(args.length == 1){
				Player p = (Player) sender;
				
				p.performCommand("whitelist add "+args[0]);
				p.sendMessage("You've invited "+ args[0]);
				
			}
		}else if(SmartServerTool.getCommands().getInvite(commandLabel) && !SmartServerTool.getPermission().hasInvite((Player)sender)){
			sender.sendMessage(SmartServerTool.noperms+" sst.invite");
		}
	}
}

package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SSTH {

	public static boolean help(CommandSender sender, String commandLabel,
			String[] args, Command cmd) {

		if (Commands.getHelp(commandLabel)) {
			
			if(args.length == 0){
				((Player) sender).performCommand("help SmartServerTool");				
			}else if (args.length == 1){
				((Player) sender).performCommand("help SmartServerTool "+args[0]);
			}else{
				sender.sendMessage("To much arguments");
			}
		}

		return true;
	}
}

package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (args.length == 0) {
			((Player) sender).performCommand("help SmartServerTool");
		} else if (args.length == 1) {
			((Player) sender).performCommand("help SmartServerTool " + args[0]);
		} else {
			sender.sendMessage("To much arguments");
		}

		return true;
	}

}

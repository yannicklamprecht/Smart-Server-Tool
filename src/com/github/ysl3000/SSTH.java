package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SSTH {

	public static boolean help(CommandSender sender, String commandLabel,
			String[] split, Command cmd) {

		if (Commands.getHelp(commandLabel)) {
			((Player) sender).performCommand("help SmartServerTool");
		}

		return true;
	}
}

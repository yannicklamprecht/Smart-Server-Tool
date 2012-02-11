package com.github.ysl3000;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Top {

	public static void toggleop(Command cmd, CommandSender sender)
			throws IOException {

		if (cmd.getName().equalsIgnoreCase("admin")) {

			if (!sender.isOp() && sender.hasPermission("MyOwn.admin")) {
				sender.setOp(true);
				sender.sendMessage((ChatColor.GREEN + "Op enabled"));
			} else if (sender.isOp() && sender.hasPermission("MyOwn.admin")) {
				sender.setOp(false);
				sender.sendMessage((ChatColor.RED + "Op disabled"));
			}
			

		}

	}
}

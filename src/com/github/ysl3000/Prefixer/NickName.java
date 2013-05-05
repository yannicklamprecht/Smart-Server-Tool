package com.github.ysl3000.Prefixer;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.ysl3000.SmartServerTool;

public class NickName {

	public static void Nick(CommandSender sender, String command,
			String[] args, Command cmd) {

		if (SmartServerTool.getCommands().getNick(command)) {

			if (args.length == 0) {
				sender.sendMessage("Use /nick <name/-r>");
			} else if (args.length == 1) {

				if (args[0].equalsIgnoreCase("-r")) {
					Prefix.Pfix((Player) sender);
					((Player) sender).sendMessage("Removed nickname");
				} else {
					
					Prefix.displayName((Player) sender, args[0]);
					((Player) sender).sendMessage("Nickname set to "
							+ ((Player) sender).getDisplayName());
					((Player) sender).setPlayerListName(ChatColor.AQUA
							+ args[0]);

				}
			}

		}

	}
}
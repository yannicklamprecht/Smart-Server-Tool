package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.Prefixer.Prefix;

public class NickName implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

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
				((Player) sender).setPlayerListName(ChatColor.AQUA + args[0]);

			}
		}
		return true;
	}
}

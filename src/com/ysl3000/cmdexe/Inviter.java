package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class Inviter implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (!((Player) sender).hasPermission(cmd.getPermission())) {
			((Player) sender).sendMessage(SmartServerTool.noperms);
			return true;
		}

		if (args.length == 0) {
			sender.sendMessage("Use /invite <player>  to invite your friends! Please write the full name!");
		} else if (args.length == 1) {
			Player p = (Player) sender;

			p.performCommand("whitelist add " + args[0]);
			p.sendMessage("You've invited " + args[0]);

		}

		return true;
	}
}

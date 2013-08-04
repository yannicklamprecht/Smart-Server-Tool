package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class ChannelChat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (!((Player) sender).hasPermission(cmd.getPermission())) {
			((Player) sender).sendMessage(SmartServerTool.noperms);
			return true;
		}

		if (args.length == 0 || args.length > 2) {

			((Player) sender)
					.sendMessage("Not enough arguments! Please use /jc yourchannelname. Or use /jc g  to join the globalchat");
		} else if (args.length == 1) {

			if (args[0].equalsIgnoreCase("g")) {
				SmartServerTool.getHSP().setChannel(
						((Player) sender).getName(), "g");
				((Player) sender).sendMessage("Reset to Global channel");
			} else if (args[0].equalsIgnoreCase("admin")) {

				((Player) sender)
						.sendMessage("You need a password for Admin-Channel! Use /jc Admin <password> !");
			} else {
				SmartServerTool.getHSP().setChannel(
						((Player) sender).getName(), "[" + args[0] + "]");
				((Player) sender).sendMessage("Channel " + args[0] + " joined");
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("admin")
					&& args[1].equalsIgnoreCase(SmartServerTool.getCFGL()
							.getAdminpassword())) {
				SmartServerTool.getHSP().setChannel(
						((Player) sender).getName(), "[Admin]");
				((Player) sender).sendMessage("Channel " + args[0] + " joined");
			} else {
				((Player) sender).sendMessage(ChatColor.RED + "Wrong password");
			}

		}

		return true;
	}
}

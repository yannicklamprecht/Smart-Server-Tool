/**
 * AdminCommand.java
 * 
 * Created on , 11:34:15 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;

import lib.CustomCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 * 
 */
public class AdminCommand extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public AdminCommand() {
		super("/admin","Toggle op", "//admin","sst.admin", new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {
						Player player = (Player) sender;

						if (args.length == 0) {
							if (!player.isOp()
									&& player
											.hasPermission(cmd.getPermission())) {

								player.setOp(true);

								player.sendMessage((ChatColor.GREEN + "Op enabled"));

							} else if (player.isOp()
									&& player
											.hasPermission(cmd.getPermission())) {

								player.setOp(false);

								player.sendMessage((ChatColor.RED + "Op disabled"));
							}
						} else if (args.length == 1) {
							player.sendMessage("please use /deop <player>");
						}

						return true;
					}
				});
	}
}

/**
 * Heal.java
 * 
 * Created on , 15:46:53 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;

/**
 * @author yannicklamprecht
 * 
 */
public class Heal extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Heal() {
		super("heal", "heal", "/heal <player>", "sst.heal",
				new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {
						if (!(sender instanceof Player))
							return false;

						Player player = (Player) sender;
						if (args.length != 1) {
							player.sendMessage("You need one argument");
							return true;
						}
						Player target = sender.getServer().getPlayer(args[0]);

						if (player.hasPermission(cmd.getPermission())) {

							if (target == null) {

								sender.sendMessage("Player not found");
								return true;

							}
							target.setHealth(20.0);
							target.setFoodLevel(20);
							target.sendMessage(ChatColor.GREEN
									+ "You have been healed by "
									+ ChatColor.DARK_PURPLE
									+ player.getDisplayName());

							player.sendMessage(ChatColor.GREEN + "You healed "
									+ ChatColor.DARK_PURPLE + target.getName());
						}

						return true;
					}
				});
	}

}

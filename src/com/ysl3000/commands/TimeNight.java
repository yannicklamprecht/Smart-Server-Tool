/**
* TimeNight.java
* 
* Created on , 14:49:55 by @author Yannick Lamprecht
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
public class TimeNight extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public TimeNight() {
		super("tn", "Set time to night", "/tn",
				"sst.time", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if(!(sender instanceof Player))return false;
						
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {
							player.getWorld().setTime(18000);
							player.sendMessage("Time set to " + ChatColor.GOLD + "Night");
						}

						return true;
					}
				});
	}

}

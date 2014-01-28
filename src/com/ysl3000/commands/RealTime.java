/**
* RealTime.java
* 
* Created on , 19:14:08 by @author Yannick Lamprecht
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

import com.ysl3000.utils.Utility;

/**
 * @author yannicklamprecht
 *
 */
public class RealTime extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public RealTime() {
		super("rt", "realtime", "/rt", "", new CommandExecutor() {
			
			@Override
			public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
					String[] args) {
				
				if(!(sender instanceof Player))return false;
				
				Player player = (Player) sender;

				player.sendMessage("Current Time "
						+ ChatColor.GOLD
						+ Utility.getTime(System.currentTimeMillis(),"HH:mm"));
				return true;
			}
		});
	}
}

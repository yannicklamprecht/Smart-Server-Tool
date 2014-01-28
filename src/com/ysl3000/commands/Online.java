/**
* Online.java
* 
* Created on , 18:50:44 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;


import lib.CustomCommand;

import org.bukkit.Bukkit;
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
public class Online extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Online() {
		super("online", "lists onlineplayer", "/online",
				"", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						if(!(sender instanceof Player))return false;
						((Player) sender).sendMessage(ChatColor.GRAY + "Online ("
								+ Bukkit.getServer().getOnlinePlayers().length + "/"
								+ Bukkit.getMaxPlayers() + "): " + Utility.listPlayers());

						return true;
					}
				});
	}
}

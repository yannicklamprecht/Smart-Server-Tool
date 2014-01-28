/**
* Storm.java
* 
* Created on , 15:06:54 by @author Yannick Lamprecht
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
public class Storm extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Storm() {
		super("storm", "Set storm", "/storm",
				"sst.weather", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						if(!(sender instanceof Player)){
							return false;
						}
						
						Player p = (Player)sender;
						if(p.hasPermission(cmd.getPermission())){
						p.getWorld().setStorm(true);
						p.getWorld().setThundering(true);
						p.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");
						}
						return true;
					}
				});
		// TODO Auto-generated constructor stub
	}

}

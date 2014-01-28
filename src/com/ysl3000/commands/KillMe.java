/**
* KillMe.java
* 
* Created on , 15:14:51 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;


import lib.CustomCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 * @author yannicklamprecht
 *
 */
public class KillMe extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public KillMe() {
		
		//TODO finishing
		super("km", "kill's yourself", "/km",
				"sst.km", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						if(!(sender instanceof Player))return false;
						
						Player player = (Player) sender;
						if (player.hasPermission(cmd.getPermission())) {
								player.setHealth(0.0);
								
						}else{
							sender.sendMessage(cmd.getPermissionMessage());
						}
						
						return true;
					}
				});
	}

}

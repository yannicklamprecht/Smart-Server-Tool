/**
* GetWeather.java
* 
* Created on , 15:11:37 by @author Yannick Lamprecht
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
public class GetWeather extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public GetWeather() {
		super("wg", "get weather", "/wg", "sst.wg", new CommandExecutor() {
			
			@Override
			public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
					String[] args) {
				if(!(sender instanceof Player))return false;
				Player player = (Player) sender;
				if (player.hasPermission(cmd.getPermission())) {
					player.sendMessage("Current Weather in " + player.getWorld().getName()+ " is "
							+ ChatColor.GOLD + (player.getWorld().isThundering())!= null?"rainy":"sunny");
				}else{
					sender.sendMessage(cmd.getPermissionMessage());
				}
				return true;
			}
		});
	}

}

/**
* SetSpawn.java
* 
* Created on , 17:35:05 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;

/**
 * @author yannicklamprecht
 *
 */
public class SetSpawn extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public SetSpawn() {
		super("setsp", "setspawn", "/setsp",
				"sst.setsp", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if(!(sender instanceof Player)) return false;
						
						if (((Player) sender).hasPermission(cmd.getPermission())) {

							Player player = (Player) sender;

							player.getWorld().setSpawnLocation(
									player.getLocation().getBlockX(),
									player.getLocation().getBlockY(),
									player.getLocation().getBlockZ());

							player.sendMessage("Spawn of " + player.getWorld().getName()
									+ " set");

						}

						return true;
					}
				});
	}

}

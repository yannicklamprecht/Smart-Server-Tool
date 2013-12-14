/**
* PlayerLookUpIp.java
* 
* Created on , 17:27:59 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;

import org.bukkit.Bukkit;
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
public class PlayerLookUpIp extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public PlayerLookUpIp() {
		super("/ip", "get ip of player",
				"//ip <player>", "sst.ip", new CommandExecutor() {
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						if(!(sender instanceof Player))return false;
						
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {
							if(args.length != 1)return false;
							Player target = Bukkit.getPlayer(args[0]);

							if (target == null) {

								player.sendMessage("PLAYER " + target + " isn't found");
								return true;
							}
							player.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
									+ ChatColor.WHITE + " is " + ChatColor.YELLOW
									+ target.getAddress());

						}
						return true;
					}
				});
	}

}

/**
* SwitchLocation.java
* 
* Created on , 17:31:35 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;


import lib.CustomCommand;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 * @author yannicklamprecht
 *
 */
public class SwitchLocation extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public SwitchLocation() {
		super("switch",
				"swap position with player",
				"/switch <player>", "sst.switch", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {

						if(!(sender instanceof Player))return false;
						
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {
							if (args.length == 0) {
								player.sendMessage("Not enough arguments");
							} else if (args.length == 1) {
								Player target = player.getServer().getPlayer(args[0]);
								if(target == null){
									player.sendMessage("Not online");
									return true;
								}

								Location loca = player.getLocation();
								player.teleport(target.getLocation());
								target.teleport(loca);

								if (target.canSee(player)) {

									player.sendMessage("You changed position with "
											+ target.getDisplayName());
									target.sendMessage(player.getDisplayName()
											+ " changed position with you. Changed by "
											+ player.getDisplayName());
								}
							} else {
								player.sendMessage("to many arguments");
							}
						}
						return true;
					}
				});
	}
}

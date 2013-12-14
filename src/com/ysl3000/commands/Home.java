/**
* Home.java
* 
* Created on , 18:31:37 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;
import com.ysl3000.utils.Permissions;

/**
 * @author yannicklamprecht
 *
 */
public class Home extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Home() {
		super("home", "teleport to home", "/home",
				"sst.home", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if(!(sender instanceof Player))return false;
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {
							if (player.getBedSpawnLocation() != null) {

								if (args.length == 0) {
									player.teleport(player.getBedSpawnLocation());
								} else if (args.length == 1) {

									if (player.hasPermission(Permissions.homeOther)) {

										if (player.getServer().getPlayer(args[0]).isOnline()) {
											Player target;
											target = player.getServer().getPlayer(args[0]);
											player.teleport(target.getPlayer()
													.getBedSpawnLocation());
										} else {
											OfflinePlayer ofp;
											ofp = player.getServer().getOfflinePlayer(args[0]);
											player.teleport(ofp.getBedSpawnLocation());
										}
									}

								}
							} else {
								player.sendMessage("No home set");
							}
						}
						return true;
					}
				});
	}

}

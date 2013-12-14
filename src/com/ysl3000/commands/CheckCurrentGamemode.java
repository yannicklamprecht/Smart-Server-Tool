/**
* CheckCurrentGamemode.java
* 
* Created on , 11:53:56 by @author Yannick Lamprecht
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
import com.ysl3000.utils.Permissions;

/**
 * @author yannicklamprecht
 *
 */
public class CheckCurrentGamemode extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public CheckCurrentGamemode() {
		super("gm", "Current GameMode", "/gm",
				"sst.gm", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {

							if (args.length == 0) {

								player.sendMessage("Current GameMode " + ChatColor.GOLD
										+ player.getGameMode());

							} else if (args.length == 1) {

								if (player.hasPermission(Permissions.gmlookupOther)) {

									Player target = Bukkit.getPlayer(args[0]);
									if(target == null){
										player.sendMessage("Player not found!");
										return false;
									}
									sender.sendMessage("Current GameMode of " + ChatColor.GOLD
											+ target.getDisplayName() + " "
											+ target.getGameMode());
								} else {

									sender.sendMessage("You aren't allowed to lookup others gamemode");
								}
							}

						}

						return true;
					}
				});
	}

}

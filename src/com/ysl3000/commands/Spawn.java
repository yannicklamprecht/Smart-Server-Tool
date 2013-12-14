/**
* Spawn.java
* 
* Created on , 17:38:21 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;

/**
 * @author yannicklamprecht
 *
 */
public class Spawn extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Spawn() {
		super("spawn", "Teleport to Spawn", "/spawn",
				"sst.spawn", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if(!(sender instanceof Player))return false;
						
						if (((Player)sender).hasPermission(cmd.getPermission())) {

							Player player = (Player) sender;

							Location lc = player.getWorld().getSpawnLocation();

							if (args.length == 0) {
								player.teleport(lc);

								player.sendMessage("Teleported to Spawn of world "
										+ ChatColor.GOLD + player.getWorld().getName());
							} else if (args.length == 1) {
								Player target = Bukkit.getPlayer(args[0]);

								target.teleport(lc);

								player.sendMessage("You teleported " + target.getDisplayName()
										+ " to Spawn");
								target.sendMessage("You were teleported to spawn by "
										+ player.getDisplayName());
							}

						}
						return true;
					}
				});
	}

}

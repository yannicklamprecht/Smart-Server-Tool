/**
* ServerInfo.java
* 
* Created on , 17:24:32 by @author Yannick Lamprecht
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
public class ServerInfo extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public ServerInfo() {
		super("serverinfo", "get Serverinfo",
				"/serverinfo", "sst.info", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						if(!(sender instanceof Player))return false;
						
						Player player = (Player) sender;
						if (player.hasPermission(cmd.getPermission())) {
							double total = (((Runtime.getRuntime().totalMemory()) / 1024) / 1024);
							double max = (((Runtime.getRuntime().maxMemory()) / 1024) / 1024);
							int cpu = Runtime.getRuntime().availableProcessors();

							sender.sendMessage("Current memoryuse "
									+ total
									+ "/"
									+ max
									+ " mb\nThis host has: "
									+ cpu
									+ " cpu's\nThe seed is : "
									+ Bukkit.getWorld(player.getWorld().getName()).getSeed()
									+ "\nServer is in "
									+ (Bukkit.getOnlineMode() ? ChatColor.GREEN + "online"
											: ChatColor.RED + "offline") + ChatColor.RESET
									+ "-mode\n" + ChatColor.GRAY + "Online ("
									+ Bukkit.getServer().getOnlinePlayers().length + "/"
									+ Bukkit.getMaxPlayers() + ")");
						}
						return true;
					}
				});
		// TODO Auto-generated constructor stub
	}

}

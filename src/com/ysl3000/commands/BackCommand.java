/**
 * BackCommand.java
 * 
 * Created on , 11:41:37 by @author Yannick Lamprecht
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

import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;

/**
 * @author yannicklamprecht
 * 
 */
public class BackCommand extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public BackCommand() {
		super("back", "tp to last location", "/back","", new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {
						
						if(!(sender instanceof Player))return false;

						Player player = (Player) sender;
						
						if (!HashMapController.getHashMapControler()
								.getSmartPLayers().containsKey(player.getUniqueId())) {
							HashMapController.getHashMapControler()
									.getSmartPLayers()
									.put(player.getUniqueId(),
											new SmartPlayer(player));
						}

						if (HashMapController.getHashMapControler().getSmartPLayers().get(player.getUniqueId())
								.getLastLocation() == null) {
							player.sendMessage("Last location not found");
							return true;
						}
						player.teleport(HashMapController.getHashMapControler().getSmartPLayers()
								.get(player.getUniqueId()).getLastLocation());
						return true;

					}
				});

	}
}

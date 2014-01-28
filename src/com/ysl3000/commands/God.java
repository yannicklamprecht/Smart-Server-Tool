/**
 * God.java
 * 
 * Created on , 18:41:01 by @author Yannick Lamprecht
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
public class God extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public God() {
		super("god", "Toggle godmode", "/god", "sst.god",
				new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {

						if (!(sender instanceof Player))
							return false;

						Player p = (Player) sender;
						if (p.hasPermission(cmd.getPermission())) {
							
							if (!HashMapController.getHashMapControler()
									.getSmartPLayers().containsKey(p.getUniqueId())) {
								HashMapController.getHashMapControler()
										.getSmartPLayers()
										.put(p.getUniqueId(),
												new SmartPlayer(p));
							}
							

							if (HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
									.isGod()) {
								HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
										.setGod(false);
							} else {
								HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId())
										.setGod(true);
							}
							p.sendMessage("Godmode set to "
									+ (HashMapController.getHashMapControler().getSmartPLayers()
											.get(p.getUniqueId()).isGod() ? "True" : "False"));

						}else{
							sender.sendMessage(cmd.getPermissionMessage());
						}

						return true;
					}
				});
	}

}

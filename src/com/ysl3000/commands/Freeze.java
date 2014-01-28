/**
* Freeze.java
* 
* Created on , 19:03:35 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;


import lib.CustomCommand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.threads.TimeAction;
import com.ysl3000.threads.TimeThread;
import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;

/**
 * @author yannicklamprecht
 *
 */
public class Freeze extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public Freeze() {
		super("freeze", "freezes a player",
				"/freeze <player>", "sst.freeze", new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if(!(sender instanceof Player))return false;
						
						Player p = (Player) sender;

						if (p.hasPermission(cmd.getPermission())) {
							if (args.length >= 1 && args.length <= 2) {

								try{
								long time = Long.parseLong(args[0]);

								if (args.length == 1) {
									freezePlayer(p, null, time);

								} else if (args.length == 2) {

									Player psender = Bukkit.getPlayer(args[1]);
									freezePlayer(p, psender, time);
								}

								}catch(NumberFormatException e){
									return true;
								}
							} else {

								p.sendMessage(ChatColor.RED + "Wrong Input");
							}

						}else{
							sender.sendMessage(cmd.getPermissionMessage());
						}
							

						return true;
						
						
					}
		
				
		private void freezePlayer(final Player p, Player sender, long time) {
			String type;
			
			if (!HashMapController.getHashMapControler()
					.getSmartPLayers().containsKey(p.getUniqueId())) {
				HashMapController.getHashMapControler()
						.getSmartPLayers()
						.put(p.getUniqueId(),
								new SmartPlayer(p));
			}
			
			
			if (!HashMapController.getHashMapControler()
					.getSmartPLayers().get(p.getUniqueId()).isFrozen()) {
				type = "Freeze ";

				new TimeThread(time, new TimeAction() {
					@Override
					public void perform() {
						HashMapController.getHashMapControler().getSmartPLayers()
						.get(p.getUniqueId()).setFrozen(true);
						
					}
				}, new TimeAction() {
					@Override
					public void perform() {
						HashMapController.getHashMapControler().getSmartPLayers()
						.get(p.getUniqueId()).setFrozen(false);
						p.sendMessage("You're now allowed to move");
					}
				});

			} else {
				HashMapController.getHashMapControler().getSmartPLayers()
						.get(p.getUniqueId()).setFrozen(false);
				type = "Smelt ";
			}
			p.sendMessage(ChatColor.BOLD + type + p.getDisplayName()
					+ ChatColor.BOLD + "!" + ChatColor.RESET);
			if (sender != null) {
				sender.sendMessage(type + " player " + p.getCustomName()
						+ " by you ");
			}
		}
		});
	}

}

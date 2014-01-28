/**
 * FlyCommand.java
 * 
 * Created on , 14:30:21 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;

import lib.CustomCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.Permissions;

/**
 * @author yannicklamprecht
 * 
 */
public class FlyMode extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public FlyMode() {
		super("fly", "toggle fly", "/fly <player>", "sst.fly",
				new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {

						if (!(sender instanceof Player)) {
							return false;
						}
						Player player = (Player) sender;

						if (player.hasPermission(cmd.getPermission())) {

							if (args.length == 0) {

								if (!player.getAllowFlight()
										&& !player.isFlying()) {

									player.setAllowFlight(true);
									player.setFlying(true);
									player.sendMessage("You can now fly ");
								} else if (player.getAllowFlight()
										&& !player.isFlying()) {
									player.setFlying(false);
									player.setAllowFlight(false);
									player.sendMessage("Fly is now disabled");
								}
							}

							else if (args.length == 1) {

								Player target = Bukkit.getPlayer(args[0]);

								if (player.hasPermission(Permissions.flyOther)) {

									if (!target.getAllowFlight()
											&& !target.isFlying()) {

										target.setAllowFlight(true);
										target.setFlying(true);
										sender.sendMessage("Set fly on for "
												+ target.getDisplayName());
										target.sendMessage("You can now fly! Allowed by "
												+ ((Player) sender)
														.getDisplayName());

									} else if (target.getAllowFlight()
											&& !target.isFlying()) {

										target.setFlying(false);
										target.setAllowFlight(false);
										sender.sendMessage("Set fly off for "
												+ target.getDisplayName());
										target.sendMessage("Until now you have to walk on feet! Disallowed by "
												+ ((Player) sender)
														.getDisplayName());
									} else if (target.getAllowFlight()
											&& target.isFlying()) {

										sender.sendMessage(target
												.getDisplayName()
												+ " is flying! Only if player is on earth you can disble that!");

									}
								} else {
									sender.sendMessage("No permission for flying others");
								}

							}

						}else{
							sender.sendMessage(cmd.getPermissionMessage());
						}
							return true;
					}
				});
	}

}

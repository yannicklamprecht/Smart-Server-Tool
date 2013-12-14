/**
 * DoneCommand.java
 * 
 * Created on , 14:27:17 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;
import com.ysl3000.utils.SmartController;

/**
 * @author yannicklamprecht
 * 
 */
public class DoneCommand extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public DoneCommand() {
		super("done", "Leave modmode", "/done", "sst.mod",
				new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {
						Player player = (Player) sender;
						if (player.hasPermission(cmd.getPermission())) {
							if (args.length == 0) {

								done(player, null);

							} else if (args.length == 1) {
								Player target = player.getServer().getPlayer(
										args[0]);
								done(target, null);
							}

						}

						return true;
					}

					private void done(Player target, Player player) {

						if (SmartController.getSmartControler().getHashmaps()
								.getSmartPLayers().get(target.getUniqueId()).isMod()) {

							target.setGameMode(GameMode.SURVIVAL);
							target.getInventory().clear();
							target.getInventory().setContents(
									SmartController.getSmartControler()
											.getHashmaps().getSmartPLayers()
											.get(target.getUniqueId())
											.getInventory());
							target.teleport(SmartController.getSmartControler()
									.getHashmaps().getSmartPLayers()
									.get(target.getUniqueId()).getModLocation());

							if (player != null) {
								player.sendMessage((ChatColor.RED
										+ "modmode disabled for " + target
										.getName()));
							}
							target.sendMessage((ChatColor.RED + "modmode disabled"));
							SmartController.getSmartControler().getHashmaps()
									.getSmartPLayers()
									.get(target.getUniqueId()).setMod(false);

						}

					}
				});
	}

}

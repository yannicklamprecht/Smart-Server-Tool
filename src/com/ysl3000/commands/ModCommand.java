/**
 * ModCommand.java
 * 
 * Created on , 14:16:34 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;

import lib.CustomCommand;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;

/**
 * @author yannicklamprecht
 * 
 */
public class ModCommand extends CustomCommand {

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public ModCommand() {
		super("mod", "enter modmode", "/mod", "sst.mod", new CommandExecutor() {

			@Override
			public boolean onCommand(CommandSender sender, Command cmd,
					String commandLabel, String[] args) {
				Player player = (Player) sender;

				if (!player.hasPermission(cmd.getPermission())) {
					sender.sendMessage(cmd.getPermissionMessage());
					return false;
				}

				if (args.length == 0) {
					setModMode(player, null, null);
				} else if (args.length >= 1 && args.length <= 2) {
					Player target = player.getServer().getPlayer(args[0]);
					if (args.length == 1) {
						setModMode(target, player, null);
					} else {
						setModMode(target, player, 3000);
					}

				}

				return true;
			}

			private void setModMode(Player target, Object... c) {

				if (!HashMapController.getHashMapControler().getSmartPLayers()
						.containsKey(target.getUniqueId())) {
					HashMapController.getHashMapControler().getSmartPLayers()
							.put(target.getUniqueId(), new SmartPlayer(target));
				}

				if (!HashMapController.getHashMapControler().getSmartPLayers()
						.get(target.getUniqueId()).isMod()) {

					if (target.getInventory().getContents().length == 0) {
						target.getInventory().addItem(
								new ItemStack(Material.AIR, 4));
					}

					SmartPlayer smartTarget = HashMapController
							.getHashMapControler().getSmartPLayers()
							.get(target.getUniqueId());

					smartTarget.setInventory(target.getInventory()
							.getContents());
					smartTarget.setModLocation(target.getLocation());

					target.getInventory().clear();
					target.setOp(true);
					target.setGameMode(GameMode.CREATIVE);
					target.sendMessage((ChatColor.GREEN + "Modmode enabled"));

					HashMapController.getHashMapControler().getSmartPLayers()
							.get(target.getUniqueId()).setMod(true);

				}
			}

		});
	}

}

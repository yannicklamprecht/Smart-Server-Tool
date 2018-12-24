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


import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 * 
 */
public class DoneCommand extends CustomCommand {


	public DoneCommand() {
		super("done", "Leave modmode", "/done", "sst.mod");
	}

	@Override
	public boolean execute(CommandSender sender, String s, String[] args) {
		if(!(sender instanceof Player))return false;

		Player player = (Player) sender;
		if (player.hasPermission(this.getPermission())) {
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

		// todo refactor properly inject smartMap

		if (!HashMapController.getHashMapControler()
				.getSmartPLayers().containsKey(target.getUniqueId())) {
			HashMapController.getHashMapControler()
					.getSmartPLayers()
					.put(target.getUniqueId(),
							new SmartPlayer(target.getPlayer()));
		}

		if (HashMapController.getHashMapControler()
				.getSmartPLayers().get(target.getUniqueId()).isMod()) {

			target.setGameMode(GameMode.SURVIVAL);
			target.getInventory().clear();
			target.getInventory().setContents(
					HashMapController.getHashMapControler().getSmartPLayers()
							.get(target.getUniqueId())
							.getInventory());
			target.teleport(HashMapController.getHashMapControler().getSmartPLayers()
					.get(target.getUniqueId()).getModLocation());

			if (player != null) {
				player.sendMessage((ChatColor.RED
						+ "modmode disabled for " + target
						.getName()));
			}
			target.sendMessage((ChatColor.RED + "modmode disabled"));
			HashMapController.getHashMapControler()
					.getSmartPLayers()
					.get(target.getUniqueId()).setMod(false);

		}

	}
}

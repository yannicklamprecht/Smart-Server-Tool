package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (((Player) sender).hasPermission(cmd.getPermission())) {

			Player player = (Player) sender;

			player.getWorld().setSpawnLocation(
					player.getLocation().getBlockX(),
					player.getLocation().getBlockY(),
					player.getLocation().getBlockZ());

			player.sendMessage("Spawn of " + player.getWorld().getName()
					+ " set");

		}

		return true;
	}

}

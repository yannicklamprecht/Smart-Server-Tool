package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class DoneCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;
		if (player.hasPermission(cmd.getPermission())) {
			if (args.length == 0) {

				done(player, null);

			} else if (args.length == 1) {
				Player target = player.getServer().getPlayer(args[0]);
				done(target, null);
			}

		}

		return true;
	}

	private void done(Player target, Player player) {

		if (SmartServerTool.getHSP().isInventoryIn(target)
				&& SmartServerTool.getHSP().hasLastLocation(target)
				&& SmartServerTool.getHSP().getIsMod(player)) {

			target.setGameMode(GameMode.SURVIVAL);
			target.getInventory().clear();
			target.getInventory().setContents(
					SmartServerTool.getHSP().getInventory(target));
			SmartServerTool.getHSP().removeInventory(target);
			target.teleport(SmartServerTool.getHSP().getLastLocation(target));
			SmartServerTool.getHSP().removeLastLocation(target);

			if (player != null) {
				player.sendMessage((ChatColor.RED + "modmode disabled for " + target
						.getName()));
			}
			target.sendMessage((ChatColor.RED + "modmode disabled"));
			SmartServerTool.getHSP().removeIsMOD(target);

		}

	}

}

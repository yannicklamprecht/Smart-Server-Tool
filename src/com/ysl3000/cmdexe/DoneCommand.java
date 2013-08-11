package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.SmartController;

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

		if (SmartController
				.getSmartControler()
				.getHashmaps()
				.getSmartPLayers()
				.get(target).isMod()) {

			target.setGameMode(GameMode.SURVIVAL);
			target.getInventory().clear();
			target.getInventory().setContents(
					SmartController
							.getSmartControler()
							.getHashmaps()
							.getSmartPLayers()
							.get(target).getInventory());
			target.teleport(SmartController
					.getSmartControler()
					.getHashmaps()
					.getSmartPLayers()
					.get(target)
					.getModLocation());

			if (player != null) {
				player.sendMessage((ChatColor.RED + "modmode disabled for " + target
						.getName()));
			}
			target.sendMessage((ChatColor.RED + "modmode disabled"));
			SmartController
					.getSmartControler()
					.getHashmaps()
					.getSmartPLayers()
					.get(target).setMod(false);

		}

	}

}

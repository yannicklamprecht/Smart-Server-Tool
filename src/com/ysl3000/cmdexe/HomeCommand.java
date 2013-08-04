package com.ysl3000.cmdexe;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.permissions.Permissions;

public class HomeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission(cmd.getPermission())) {
			if (player.getBedSpawnLocation() != null) {

				if (args.length == 0) {
					player.teleport(player.getBedSpawnLocation());
				} else if (args.length == 1) {

					if (player.hasPermission(Permissions.homeOther)) {

						if (player.getServer().getPlayer(args[0]).isOnline()) {
							Player target;
							target = player.getServer().getPlayer(args[0]);
							player.teleport(target.getPlayer()
									.getBedSpawnLocation());
						} else {
							OfflinePlayer ofp;
							ofp = player.getServer().getOfflinePlayer(args[0]);
							player.teleport(ofp.getBedSpawnLocation());
						}
					}

				}
			} else {
				player.sendMessage("No home set");
			}
		}

		return true;
	}

}

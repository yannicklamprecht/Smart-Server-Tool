package com.ysl3000.cmdexe;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SwitchCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission(cmd.getPermission())) {
			if (args.length == 0) {
				player.sendMessage("Not enough arguments");
			} else if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				Location loca = player.getLocation();
				player.teleport(target.getLocation());
				target.teleport(loca);

				if (target.canSee(player)) {

					player.sendMessage("You changed position with "
							+ target.getDisplayName());
					target.sendMessage(player.getDisplayName()
							+ " changed position with you. Changed by "
							+ player.getDisplayName());
				}

			} else {
				player.sendMessage("to many arguments");
			}

		}
		return true;
	}

}

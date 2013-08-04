package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerKillCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (player.hasPermission(cmd.getPermission())) {

			if (args.length == 0) {
				player.sendMessage("Need more arguments");
			} else if (args.length == 1) {

				Player target = sender.getServer().getPlayer(args[0]);

				if (target == null) {
					player.sendMessage("Player not found");
					return true;
				}
				target.setHealth(0.0);
				player.sendMessage(target.getDisplayName()
						+ " killed successfully");
			}
		}
		return true;
	}
}

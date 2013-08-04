package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.permissions.Permissions;

public class SurvivalGamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;
		if (player.hasPermission(cmd.getPermission())) {

			if (args.length == 0) {

				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("Enter " + ChatColor.GOLD
						+ player.getGameMode());

			} else if (args.length == 1
					&& player.hasPermission(Permissions.gamemodeOther)) {

				Player target = Bukkit.getPlayer(args[0]);

				if (target == null) {

					sender.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.SURVIVAL);

				target.sendMessage(ChatColor.GREEN + "You entered "
						+ target.getGameMode() + " set by "
						+ ChatColor.DARK_PURPLE + sender.getName());

				sender.sendMessage(ChatColor.GREEN + "Gamemode set to  "
						+ target.getGameMode() + " " + ChatColor.DARK_PURPLE +

						target.getName());
			}

		}

		return true;
	}

}

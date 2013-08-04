package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		
		if (((Player)sender).hasPermission(cmd.getPermission())) {

			Player player = (Player) sender;

			Location lc = player.getWorld().getSpawnLocation();

			if (args.length == 0) {
				player.teleport(lc);

				player.sendMessage("Teleported to Spawn of world "
						+ ChatColor.GOLD + player.getWorld().getName());
			} else if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);

				target.teleport(lc);

				player.sendMessage("You teleported " + target.getDisplayName()
						+ " to Spawn");
				target.sendMessage("You were teleported to spawn by "
						+ player.getDisplayName());
			}

		}

		
		
		
		return true;
	}

}

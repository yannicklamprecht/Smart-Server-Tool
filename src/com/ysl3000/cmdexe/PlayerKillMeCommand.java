package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerKillMeCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {

		Player player = (Player) sender;
		if (player.hasPermission(cmd.getPermission())) {
				player.setHealth(0.0);
				
		}
		
		return true;
	}

}

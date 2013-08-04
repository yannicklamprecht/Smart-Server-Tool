package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class BackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {

		Player player = (Player) sender;

		if (SmartServerTool.getHSP().getLastLocation(player) == null) {
			player.sendMessage("Last location not found");
			return true;
		}
		player.teleport(SmartServerTool.getHSP().getLastLocation(player));
		return true;
	}

}

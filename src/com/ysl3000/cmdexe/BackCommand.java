package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.SmartController;

public class BackCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,
			String[] arg3) {

		Player player = (Player) sender;

		if (SmartController.getSmartControler().getHashmaps().getSmartPLayers()
				.get(player).getLastLocation() == null) {
			player.sendMessage("Last location not found");
			return true;
		}
		player.teleport(SmartController.getSmartControler().getHashmaps()
				.getSmartPLayers().get(player).getLastLocation());
		return true;
	}

}

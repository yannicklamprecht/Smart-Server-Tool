package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.SmartController;

public class ToggleGodModeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] arg3) {

		Player p = (Player) sender;
		if (p.hasPermission(cmd.getPermission())) {

			if (SmartController.getSmartControler().getHashmaps()
					.getSmartPLayers().get(p).isGod()) {
				SmartController.getSmartControler().getHashmaps()
						.getSmartPLayers().get(p).setGod(false);
			} else {
				SmartController.getSmartControler().getHashmaps()
						.getSmartPLayers().get(p).setGod(true);
			}
			p.sendMessage("Godmode set to "
					+ (SmartController.getSmartControler().getHashmaps()
							.getSmartPLayers().get(p).isGod() ? "True"
							: "False"));

		}

		return true;
	}

}

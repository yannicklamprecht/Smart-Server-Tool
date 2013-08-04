package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class ToggleGodModeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] arg3) {

		Player p = (Player) sender;
		if (p.hasPermission(cmd.getPermission())) {

			if (SmartServerTool.getHSP().getIsMod(p)) {
				SmartServerTool.getHSP().removeIsMOD(p);
			} else {
				SmartServerTool.getHSP().setIsMOD(p);
			}
			p.sendMessage("Godmode set to "
					+ SmartServerTool.getHSP().getIsMod(p));

		}

		return true;
	}

}

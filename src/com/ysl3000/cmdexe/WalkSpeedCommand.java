package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WalkSpeedCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if(args.length!=1)return false;
		
		
		Player p = (Player) sender;

		if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
			p.setWalkSpeed(Float.parseFloat(args[0]));
			p.sendMessage("Walkspeed set to " + p.getWalkSpeed());
		} else {
			p.sendMessage("Speed has to be between 0.1 and 1.0");
		}

		return true;
	}

}

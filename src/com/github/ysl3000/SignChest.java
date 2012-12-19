package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SignChest {

	public static void Chest(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player p = (Player) sender;

		if (Commands.getCommandWorkbench(command)) {

			p.openWorkbench(p.getLocation(), true);
		} else if (Commands.getCommandEchantTable(command)) {
			p.openEnchanting(p.getLocation(), true);
		} else if (Commands.getEnderChest(command)) {
			p.openInventory(p.getEnderChest());
		} 

	}

}

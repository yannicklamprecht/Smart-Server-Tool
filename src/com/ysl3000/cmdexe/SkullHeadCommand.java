package com.ysl3000.cmdexe;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;
import com.github.ysl3000.Item.ScullHeadType;

public class SkullHeadCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission())) {
			player.sendMessage(SmartServerTool.noperms);
			return true;
		}

		if (args.length == 1) {

			if (args[0].equalsIgnoreCase("psk")) {
				player.getInventory().addItem(
						ScullHeadType.Skull(player.getName()));
				player.sendMessage("A Skullhead from " + player.getName()
						+ " is added");
			} else {

				player.performCommand("give " + sender.getName() + " "
						+ args[0] + " "
						+ SmartServerTool.getCFGL().getDefaultStack());

			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("psk")) {
				player.getInventory().addItem(ScullHeadType.Skull(args[1]));
				player.sendMessage("A Skullhead from " + args[1] + " is added");
			} else {
				player.performCommand("give " + sender.getName() + " "
						+ args[0] + " " + args[1]);
			}
		}

		return true;
	}

}

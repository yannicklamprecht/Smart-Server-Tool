package com.github.ysl3000;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class ItemMan {

	public static void item(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player player = (Player) sender;

		if (Commands.getI(command) && Permission.hasItem((Player)sender)) {

			if (args.length == 1) {

				player.performCommand("give " + sender.getName() + " "
						+ args[0] + " " + ConfigLoader.getDefaultStack());
			} else if (args.length == 2) {

				player.performCommand("give " + sender.getName() + " "
						+ args[0] + " " + args[1]);
			}

		} else if (Commands.getCI(command)
				&& Permission.hasClearInventory((Player)sender)) {

			if (sender instanceof Player) {
				player.getInventory().clear();
				player.sendMessage("Inventory cleared");
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		} else if (Commands.getRepair(command) && Permission.hasRepair((Player)sender)) {

			if (sender instanceof Player) {
				Map<Enchantment, Integer> ench = player.getItemInHand()
						.getEnchantments();

				short du = 0;
				player.getItemInHand().setDurability(du);
				player.getItemInHand().addEnchantments(ench);
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		}

	}

}

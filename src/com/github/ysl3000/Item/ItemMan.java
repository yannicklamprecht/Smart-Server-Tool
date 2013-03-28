package com.github.ysl3000.Item;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class ItemMan {

	public static void item(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player player = (Player) sender;

		if (SmartServerTool.getCommands().getI(command) && SmartServerTool.getPermission().hasItem((Player)sender)) {

			if (args.length == 1) {

				if(args[0].equalsIgnoreCase("psk")){
					player.getInventory().addItem(ScullHeadType.Skull(player.getName()));
					player.sendMessage("A Skullhead from "+ player.getName()+" is added");
				}else{
				
					player.performCommand("give " + sender.getName() + " "
							+ args[0] + " " + SmartServerTool.getCFGL().getDefaultStack());
					
				}
			} else if (args.length == 2) {
				if(args[0].equalsIgnoreCase("psk")){
					player.getInventory().addItem(ScullHeadType.Skull(args[1]));
					player.sendMessage("A Skullhead from "+ args[1]+" is added");
				}else{
				player.performCommand("give " + sender.getName() + " "
						+ args[0] + " " + args[1]);
				}
			}

		} else if (SmartServerTool.getCommands().getCI(command)
				&& SmartServerTool.getPermission().hasClearInventory((Player)sender)) {

			if (sender instanceof Player) {
				player.getInventory().clear();
				player.sendMessage("Inventory cleared");
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		} else if (SmartServerTool.getCommands().getRepair(command) && SmartServerTool.getPermission().hasRepair((Player)sender)) {

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

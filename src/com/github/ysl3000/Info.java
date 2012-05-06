package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Info {

	public static boolean infos(CommandSender sender, String commandLabel,
			String[] split, Command cmd) throws Exception {

		
		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("/mem")
				&& player.hasPermission("sst.mem")) {

			double total = (((Runtime.getRuntime().totalMemory()) / 1024) / 1024);

			double max = (((Runtime.getRuntime().maxMemory()) / 1024) / 1024);

			sender.sendMessage(" Current memoryuse " + total + "/" + max
					+ " mb");

		} else if (cmd.getName().equalsIgnoreCase("/ip")
				&& sender.hasPermission("sst.ip")) {

			Player target = Bukkit.getPlayer(split[0]);

			if (target == null) {

				sender.sendMessage("PLAYER " + target + " isn't found");

			} else {
				sender.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
						+ ChatColor.WHITE + " is " + ChatColor.YELLOW
						+ target.getAddress());

			}

		} else if (cmd.getName().equalsIgnoreCase("/cpu")) {

			int cpu = Runtime.getRuntime().availableProcessors();

			sender.sendMessage("This host has: " + cpu + " cpu's");
		} else if (cmd.getName().equalsIgnoreCase("seed")) {

			long ch = 0;
			if (sender instanceof Player) {

				ch = Bukkit.getWorld(player.getWorld().getName()).getSeed();

				sender.sendMessage("The seed is : " + ch);
			} else {
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

		} else if (commandLabel.equalsIgnoreCase("sstv")) {

			player.performCommand("version SmartServerTool");

		}
		return true;

	}
}

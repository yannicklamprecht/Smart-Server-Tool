package com.github.ysl3000;

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
			
			double total = (((Runtime.getRuntime().totalMemory())/1024)/1024)  ;

			double max  = (((Runtime.getRuntime().maxMemory())/1024)/1024);
			
			
			player.sendMessage(" Current memoryuse "
					+ total + "/"
					+ max + " mb");

		} else if (cmd.getName().equalsIgnoreCase("/ip")
				&& sender.hasPermission("sst.ip")) {

			Player target = player.getServer().getPlayer(split[0]);

			if (target == null) {

				sender.sendMessage("PLAYER " + target + " isn't found");

			} else {
				player.sendMessage("Ip of " + ChatColor.GOLD+ target.getName() + ChatColor.WHITE+ " is "
						+  ChatColor.YELLOW+ target.getAddress());

			}

		}else if (cmd.getName().equalsIgnoreCase("/lag")){
			
			double cpu = Runtime.getRuntime().availableProcessors();
			
			
			player.sendMessage("The current CPU ussage is: "+cpu);
		}
		return true;

	}
}

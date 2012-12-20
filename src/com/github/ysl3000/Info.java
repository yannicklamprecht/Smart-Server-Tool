package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Info {

	public static boolean infos(CommandSender sender, String commandLabel,
			String[] split, Command cmd) throws Exception {

		Player player = (Player) sender;

		if (Commands.getMem(commandLabel) && Permission.hasMem((Player) sender)) {

			double total = (((Runtime.getRuntime().totalMemory()) / 1024) / 1024);

			double max = (((Runtime.getRuntime().maxMemory()) / 1024) / 1024);

			sender.sendMessage(" Current memoryuse " + total + "/" + max
					+ " mb");

		} else if (Commands.getIp(commandLabel)
				&& Permission.hasIp((Player) sender)) {

			Player target = Bukkit.getPlayer(split[0]);

			if (target == null) {

				sender.sendMessage("PLAYER " + target + " isn't found");

			} else {
				sender.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
						+ ChatColor.WHITE + " is " + ChatColor.YELLOW
						+ target.getAddress());

			}

		} else if (Commands.getCpu(commandLabel)) {

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

		} else if (Commands.getSSTV(commandLabel)) {

			player.performCommand("version SmartServerTool");

		} else if (Commands.getOnlinemode(commandLabel)) {
			player.sendMessage("Server is in "
					+ (Bukkit.getOnlineMode() ? ChatColor.GREEN + "online"
							: ChatColor.RED + "offline") + ChatColor.RESET
					+ "-mode");
		} else if (Commands.getListPlayers(commandLabel)) {
			player.sendMessage(ChatColor.GRAY + "Online ("
					+ Bukkit.getServer().getOnlinePlayers().length + "/"
					+ Bukkit.getMaxPlayers() + "): " + MOTD.listPlayers());
		}  else if (Commands.getSeenPlayer(commandLabel)) {
			if (split.length == 0) {
				player.sendMessage("Use /seen <player>");
			} else if (split.length == 1) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(split[0]);

				if(op.hasPlayedBefore()){
				player.sendMessage(ChatColor.GREEN
						+ "Player was first seen on: "
						+ ChatColor.GOLD
						+ DateTime.getRealTime("MMM dd yyyy HH:mm",
								op.getFirstPlayed())
						+ "\n"
						+ ChatColor.GREEN
						+ "and last seen on: "+ChatColor.GOLD
						+ DateTime.getRealTime("MMM dd yyyy HH:mm",
								op.getLastPlayed()));
				}else{
					player.sendMessage(ChatColor.GREEN+"Player "+ChatColor.GOLD+op.getName()+ChatColor.GREEN+" never seen before!");
				}
			}else if(split.length >1){
				player.sendMessage(ChatColor.RED+"To much arguments");
			}

		}else if(Commands.getRealTime(commandLabel)){
			
			player.sendMessage("The current time is "+ DateTime.getRealTime("HH:mm", System.currentTimeMillis()));
			
		}else if(Commands.getPluginsCommand(commandLabel)){
			if(!player.hasPermission("bukkit.command.plugins")){
				player.kickPlayer("You aren't allowed to lookup plugins");
			}
		}
		
		return true;
	}
}

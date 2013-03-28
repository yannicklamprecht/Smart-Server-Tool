package com.github.ysl3000.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;
import com.github.ysl3000.Prefixer.MOTD;

public class Info {

	public static boolean infos(CommandSender sender, String commandLabel,
			String[] split, Command cmd) throws Exception {

		Player player = (Player) sender;

		if (SmartServerTool.getCommands().getMem(commandLabel) && SmartServerTool.getPermission().hasMem((Player) sender)) {

			double total = (((Runtime.getRuntime().totalMemory()) / 1024) / 1024);

			double max = (((Runtime.getRuntime().maxMemory()) / 1024) / 1024);

			sender.sendMessage(" Current memoryuse " + total + "/" + max
					+ " mb");

		} else if (SmartServerTool.getCommands().getIp(commandLabel)
				&& SmartServerTool.getPermission().hasIp((Player) sender)) {

			Player target = Bukkit.getPlayer(split[0]);

			if (target == null) {

				sender.sendMessage("PLAYER " + target + " isn't found");

			} else {
				sender.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
						+ ChatColor.WHITE + " is " + ChatColor.YELLOW
						+ target.getAddress());

			}

		} else if (SmartServerTool.getCommands().getCpu(commandLabel)) {

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

		} else if (SmartServerTool.getCommands().getSSTV(commandLabel)) {

			player.performCommand("version SmartServerTool");

		} else if (SmartServerTool.getCommands().getOnlinemode(commandLabel)) {
			player.sendMessage("Server is in "
					+ (Bukkit.getOnlineMode() ? ChatColor.GREEN + "online"
							: ChatColor.RED + "offline") + ChatColor.RESET
					+ "-mode");
		} else if (SmartServerTool.getCommands().getListPlayers(commandLabel)) {
			player.sendMessage(ChatColor.GRAY + "Online ("
					+ Bukkit.getServer().getOnlinePlayers().length + "/"
					+ Bukkit.getMaxPlayers() + "): " + MOTD.listPlayers());
		}  else if (SmartServerTool.getCommands().getSeenPlayer(commandLabel)) {
			if (split.length == 0) {
				player.sendMessage("Use /seen <player>");
			} else if (split.length == 1) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(split[0]);

				if(op.hasPlayedBefore()){
				player.sendMessage(ChatColor.GREEN
						+ "Player was first seen on: "
						+ ChatColor.GOLD
						+ SmartServerTool.getDateTime().getRealTime("MMM dd yyyy HH:mm",
								op.getFirstPlayed())
						+ "\n"
						+ ChatColor.GREEN
						+ "and last seen on: "+ChatColor.GOLD
						+ SmartServerTool.getDateTime().getRealTime("MMM dd yyyy HH:mm",
								op.getLastPlayed()));
				}else{
					player.sendMessage(ChatColor.GREEN+"Player "+ChatColor.GOLD+op.getName()+ChatColor.GREEN+" never seen before!");
				}
			}else if(split.length >1){
				player.sendMessage(ChatColor.RED+"To much arguments");
			}

		}else if(SmartServerTool.getCommands().getRealTime(commandLabel)){
			
			player.sendMessage("The current time is "+ SmartServerTool.getDateTime().getRealTime("HH:mm", System.currentTimeMillis()));
			
		}else if(SmartServerTool.getCommands().getPluginsCommand(commandLabel)){
			if(!SmartServerTool.getPermission().hasBukkitCommandPlugins(player)){
				player.kickPlayer("You aren't allowed to lookup plugins");
			}
		}
		
		return true;
	}
}

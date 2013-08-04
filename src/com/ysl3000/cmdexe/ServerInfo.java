package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerInfo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;
		if (player.hasPermission(cmd.getPermission())) {
			double total = (((Runtime.getRuntime().totalMemory()) / 1024) / 1024);
			double max = (((Runtime.getRuntime().maxMemory()) / 1024) / 1024);
			int cpu = Runtime.getRuntime().availableProcessors();

			sender.sendMessage("Current memoryuse "
					+ total
					+ "/"
					+ max
					+ " mb\nThis host has: "
					+ cpu
					+ " cpu's\nThe seed is : "
					+ Bukkit.getWorld(player.getWorld().getName()).getSeed()
					+ "\nServer is in "
					+ (Bukkit.getOnlineMode() ? ChatColor.GREEN + "online"
							: ChatColor.RED + "offline") + ChatColor.RESET
					+ "-mode\n" + ChatColor.GRAY + "Online ("
					+ Bukkit.getServer().getOnlinePlayers().length + "/"
					+ Bukkit.getMaxPlayers() + ")");
		}
		return true;
	}
}

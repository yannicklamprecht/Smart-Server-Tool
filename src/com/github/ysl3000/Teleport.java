package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;


public class Teleport {

	public static boolean tp(Player player, String command, String[] args,
			Command cmd) throws Exception {

		tpm(player, command, args, cmd);
		
		home(player, command, args, cmd);
		
		return true;

	}
	public static void tpm(Player player, String command, String[] args,
			Command cmd)throws Exception{
		if (cmd.getName().equalsIgnoreCase("tp")
				&& player.hasPermission("sst.tpt")) {

			if (args.length == 0) {
				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				player.teleport(target);
				player.sendMessage("Teleported to " + ChatColor.GOLD+target.getName());
				target.sendMessage(ChatColor.GOLD+player.getName() +ChatColor.WHITE+ " teleported to you");
			}

		} else if (cmd.getName().equalsIgnoreCase("tpo")
				&& player.hasPermission("sst.tpo")) {

			if (args.length == 0) {

				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				target.teleport(player);

				player.sendMessage("You Teleported " + ChatColor.GREEN+target.getName()
						+ " to you");
				target.sendMessage(ChatColor.GOLD + player.getName() + ChatColor.WHITE+" teleported " + ChatColor.GREEN+target.getName() + ChatColor.WHITE+" to "
						+ ChatColor.GOLD+ player.getName());
			}

		} else if (cmd.getName().equalsIgnoreCase("switch")
				&& player.hasPermission("sst.switch")) {

			if (args.length == 0) {
				player.sendMessage("Not enough arguments");
			} else if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);
				Player temp = player.getPlayer();

				player.teleport(target.getLocation());
				target.teleport(temp.getLocation());

				player.sendMessage("You changed position with "
						+ ChatColor.GREEN + target.getName());
				target.sendMessage(player.getName()
						+ " changed position with you. Changed by "
						+ player.getName());

			} else {
				player.sendMessage("to many arguments");
			}
		}
	}

	public static void home(Player player, String command, String[] args,
			Command cmd)throws Exception{
		
		
		if (cmd.getName().equalsIgnoreCase("home")&& player.hasPermission("sst.home")){
			if(player.getBedSpawnLocation() != null){
				
				if(args.length == 0){
					
					player.teleport(player.getBedSpawnLocation());
				}else if (args.length == 1 && player.hasPermission("sst.homeo")){
					
					Player target = player.getServer().getPlayer(args[0]);
					player.teleport(target.getLocation());
				}
			}else{
				player.sendMessage("No home set");
			}
			
			
		}else if (cmd.getName().equalsIgnoreCase("seth")&& player.hasPermission("sst.seth")){
			player.setBedSpawnLocation(player.getLocation());
			
		}
	}

	
}

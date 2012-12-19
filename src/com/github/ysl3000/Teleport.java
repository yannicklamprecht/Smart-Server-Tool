package com.github.ysl3000;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
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
			Command cmd) throws Exception {
		if (Commands.getTP(command)
				&& Permission.hasCanTp(player)) {

			if (args.length == 0) {
				player.sendMessage("Wrong Input");
			} else if(args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				player.teleport(target);
				if(target.canSee(player)){
					player.sendMessage("Teleported to " + target.getDisplayName());
					target.sendMessage(player.getDisplayName() + " teleported to you");
				}
				
			}else if(args.length == 2){
				Player target1 = player.getServer().getPlayer(args[0]);
				Player target2 = player.getServer().getPlayer(args[1]);
				
				if(target1 == null || target2 == null){
					player.sendMessage("One player isn't online");
					return;
				}
				target1.teleport(target2);
				player.sendMessage("You teleported "+ target1.getDisplayName()+" to "+target2.getDisplayName());
				
			}else if(args.length == 3){
				
				double x = Double.parseDouble(args[0]);
				double y = Double.parseDouble(args[1]);
				double z = Double.parseDouble(args[2]);
				player.teleport(new Location(player.getWorld(), x, y, z));
				player.sendMessage("You teleported to x: "+ x +" y: "+y+" z: "+z);
			}
				

		} else if (Commands.getTPO(command)
				&& Permission.hasCanTpO(player)) {

			if (args.length == 0) {

				player.sendMessage("Wrong Input");
			} else {

				Player target = player.getServer().getPlayer(args[0]);

				target.teleport(player);

				if(target.canSee(player)){
					player.sendMessage("You Teleported " + target.getDisplayName()
							+ " to you");
					target.sendMessage(player.getDisplayName() + " teleported "
							+ target.getDisplayName() + " to "
							+ player.getDisplayName());
				}
				
			}

		} else if (cmd.getName().equalsIgnoreCase("switch")
				&& Permission.hasCanSwitch(player)) {

			if (args.length == 0) {
				player.sendMessage("Not enough arguments");
			} else if (args.length == 1) {

				Player target = player.getServer().getPlayer(args[0]);

				Location loca = player.getLocation();
				player.teleport(target.getLocation());
				target.teleport(loca);

				if(target.canSee(player)){
					
					player.sendMessage("You changed position with "
							+ target.getDisplayName());
					target.sendMessage(player.getDisplayName()
							+ " changed position with you. Changed by "
							+ player.getDisplayName());
				}
				

			} else {
				player.sendMessage("to many arguments");
			}
		} else if (Commands.getBack(command)) {

			player.teleport(HashmapHandler.getLastLocation(player));
			
			

		}
	}

	public static void home(Player player, String command, String[] args,
			Command cmd) throws Exception {

		if (Commands.getHome(command)
				&& Permission.hasHome(player)) {
			if (player.getBedSpawnLocation() != null) {

				if (args.length == 0) {

					player.teleport(player.getBedSpawnLocation());
				} else if (args.length == 1
						&& Permission.hasHomeO(player)) {

					
					if(player.getServer().getPlayer(args[0]).isOnline()){
						Player target;
						target = player.getServer().getPlayer(args[0]);
						player.teleport(target.getPlayer().getBedSpawnLocation());
					}else{
						OfflinePlayer ofp;
						ofp = player.getServer().getOfflinePlayer(args[0]);
						player.teleport(ofp.getBedSpawnLocation());
					}
					
					
				}
			} else {
				player.sendMessage("No home set");
			}

		}
	}

}

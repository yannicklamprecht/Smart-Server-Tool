package com.github.ysl3000;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gm {

	static HashMap<Player, ItemStack[]> pinv = new HashMap<Player, ItemStack[]>();

	public static boolean toggleGm(Player player, String command,
			String[] args, Command cmd) throws Exception {

		gameMC(player, command, args, cmd);
		fly(player, command, args, cmd);

		return false;

	}

	public static boolean gameMC(CommandSender sender, String command, String[] args,
			Command cmd) {

		
		Player player = (Player) sender;
		if (command.equalsIgnoreCase("gmc") && sender.hasPermission("sst.gmc")) {

			if (args.length == 0) {
				if(sender instanceof Player){

					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage("Enter " + ChatColor.GOLD + " Creative mode");
				}else{
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

			} else if (args.length == 1 && sender.hasPermission("sst.gmco")) {

				Player target = Bukkit.getPlayer(args[0]);

				if (target == null) {

					sender.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.CREATIVE);

				target.sendMessage(ChatColor.GREEN + "You Gamemode now is "
						+ target.getGameMode() + ", set by "
						+ ChatColor.DARK_PURPLE + sender.getName());

				sender.sendMessage(ChatColor.GREEN + "You set "
						+ target.getGameMode() + " for "
						+ ChatColor.DARK_PURPLE + target.getName());
			}

		} else if (command.equalsIgnoreCase("gms")
				&& sender.hasPermission("sst.gms")) {

			if (args.length == 0) {
				
				if(sender instanceof Player){
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("Enter " + ChatColor.GOLD
							+ player.getGameMode());
				}else{
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

				
			} else if (args.length == 1 && sender.hasPermission("sst.gmso")) {

				Player target = Bukkit.getPlayer(args[0]);

				if (target == null) {

					sender.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.SURVIVAL);

				target.sendMessage(ChatColor.GREEN + "You entered "
						+ target.getGameMode() + " set by "
						+ ChatColor.DARK_PURPLE + sender.getName());

				sender.sendMessage(ChatColor.GREEN + "Gamemode set to  "
						+ target.getGameMode() + " " + ChatColor.DARK_PURPLE +

						target.getName());
			}

		} else if (command.equalsIgnoreCase("gm")
				&& player.hasPermission("sst.gm")) {

			if (args.length == 0) {
				
				if(sender instanceof Player){
					player.sendMessage("Current GameMode " + ChatColor.GOLD
							+ player.getGameMode());
				}else{
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

				

			} else if (args.length == 1) {

				if (sender.hasPermission("sst.gmo")) {

					Player target = Bukkit.getPlayer(args[0]);
					sender.sendMessage("Current GameMode of " + ChatColor.GOLD
							+ target.getDisplayName() + " "
							+ target.getGameMode());
				} else if (!sender.hasPermission("sst.gmo")) {

					sender.sendMessage("You aren't allowed to lookup others gamemode");
				}

			}

		}
		return false;

	}

	public static boolean fly(CommandSender sender, String command, String[] args,
			Command cmd) {

		Player player = (Player)sender;
		if (command.equalsIgnoreCase("fly") && sender.hasPermission("sst.fly")) {

			if (args.length == 0) {
				
				if(sender instanceof Player){
					if (!player.getAllowFlight() && !player.isFlying()) {

						player.setAllowFlight(true);
						player.setFlying(true);
						player.sendMessage("You can now fly ");

					} else if (player.getAllowFlight() && !player.isFlying()) {
						player.setFlying(false);
						player.setAllowFlight(false);
						player.sendMessage("Fly is now disabled");
					}
				}else{
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

				

			} else if (args.length == 1) {

				Player target = Bukkit.getPlayer(args[0]);

				if (sender.hasPermission("sst.flyo")) {

					if (!target.getAllowFlight() && !target.isFlying()) {

						target.setAllowFlight(true);
						target.setFlying(true);
						sender.sendMessage("Set fly on for " + target.getName());
						target.sendMessage("You can now fly! Allowed by "
								+ sender.getName());

					} else if (target.getAllowFlight() && !target.isFlying()) {

						target.setFlying(false);
						target.setAllowFlight(false);
						sender.sendMessage("Set fly off for "
								+ target.getName());
						target.sendMessage("Until now you have to walk on feet! Disallowed by "
								+ sender.getName());
					}
				} else {

					sender.sendMessage("No permission for flying others");

				}

			}
		} else if (command.equalsIgnoreCase("fly")
				&& !player.hasPermission("sst.fly")) {

			sender.sendMessage("No Permission for flying");

		}

		return false;

	}

}

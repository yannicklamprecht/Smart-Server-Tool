package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gm {

	public static boolean toggleGm(Player player, String command,
			String[] args, Command cmd) throws Exception {

		gameMC(player, command, args, cmd);
		fly(player, command, args, cmd);

		return false;

	}

	public static boolean gameMC(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player player = (Player) sender;
		if (command.equalsIgnoreCase("gmc") && sender.hasPermission("sst.gamemode")) {

			if (args.length == 0) {
				if (sender instanceof Player) {

					player.setGameMode(GameMode.CREATIVE);
					player.sendMessage("Enter " + ChatColor.GOLD
							+ " Creative mode");
				} else {
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

			} else if (args.length == 1 && sender.hasPermission("sst.gamemodeo")) {

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
				&& sender.hasPermission("sst.gamemode")) {

			if (args.length == 0) {

				if (sender instanceof Player) {
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("Enter " + ChatColor.GOLD
							+ player.getGameMode());
				} else {
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

			} else if (args.length == 1 && sender.hasPermission("sst.gamemodeo")) {

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

				if (sender instanceof Player) {
					player.sendMessage("Current GameMode " + ChatColor.GOLD
							+ player.getGameMode());
				} else {
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

	public static boolean fly(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player player = (Player) sender;
		if (command.equalsIgnoreCase("fly") && sender.hasPermission("sst.fly")) {

			if (args.length == 0) {

				if (sender instanceof Player) {
					if (!player.getAllowFlight() && !player.isFlying()) {

						player.setAllowFlight(true);
						player.setFlying(true);
						player.sendMessage("You can now fly ");
						HashmapHandler.setFlyStatus(player, true);

					} else if (player.getAllowFlight() && !player.isFlying()) {
						player.setFlying(false);
						player.setAllowFlight(false);
						player.sendMessage("Fly is now disabled");
						HashmapHandler.setFlyStatus(player, false);
					}
				} else {
					sender.sendMessage(SmartServerTool.consolehasperformed);
				}

			} else if (args.length == 1) {

				Player target = Bukkit.getPlayer(args[0]);

				if (sender.hasPermission("sst.flyo")) {

					if (!target.getAllowFlight() && !target.isFlying()) {

						target.setAllowFlight(true);
						target.setFlying(true);
						sender.sendMessage("Set fly on for "
								+ target.getDisplayName());
						target.sendMessage("You can now fly! Allowed by "
								+ ((Player) sender).getDisplayName());
						HashmapHandler.setFlyStatus(target, true);

					} else if (target.getAllowFlight() && !target.isFlying()) {

						target.setFlying(false);
						target.setAllowFlight(false);
						sender.sendMessage("Set fly off for "
								+ target.getDisplayName());
						target.sendMessage("Until now you have to walk on feet! Disallowed by "
								+ ((Player) sender).getDisplayName());
						HashmapHandler.setFlyStatus(target, false);
					} else if (target.getAllowFlight() && target.isFlying()) {

						sender.sendMessage(target.getDisplayName()
								+ " is flying! Only if player is on earth you can disble that!");

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

	public static boolean playerSpeed(CommandSender sender, String command,
			String[] args, Command cmd) {

		if (command.equalsIgnoreCase("fs")) {
			Player p = (Player) sender;

			if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
				p.setFlySpeed(Float.parseFloat(args[0]));
				p.sendMessage("Flyspeed set to " + p.getFlySpeed());
			} else {
				p.sendMessage("Speed has to be between 0.1 and 1.0");
			}

		} else if (command.equalsIgnoreCase("ws")) {
			Player p = (Player) sender;

			if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
				p.setWalkSpeed(Float.parseFloat(args[0]));

				p.sendMessage("walkspeed set to " + p.getWalkSpeed());
			} else {
				p.sendMessage("Speed has to be between 0.1 and 1.0");
			}

		}

		return false;
	}

	public static boolean godmode(CommandSender sender, String command,
			String[] args, Command cmd) {

		Player p = (Player) sender;
		if (command.equalsIgnoreCase("god") && p.hasPermission("sst.god")) {

			if (HashmapHandler.isGod(p)) {
				HashmapHandler.setGod(p, false);
			} else {
				HashmapHandler.setGod(p, true);
			}
			p.sendMessage("Godmode set to " + HashmapHandler.isGod(p));

		}

		return false;
	}
}

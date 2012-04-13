package com.github.ysl3000;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
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

	public static boolean gameMC(Player player, String command, String[] args,
			Command cmd) {

		if (command.equalsIgnoreCase("gmc") && player.hasPermission("sst.gmc")) {

			if (args.length == 0) {

				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage("Enter " + ChatColor.GOLD + " Creative mode");
			} else if (args.length == 1 && player.hasPermission("sst.gmco")) {

				Player target = player.getServer().getPlayer(args[0]);

				if (target == null) {

					player.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.CREATIVE);

				target.sendMessage(ChatColor.GREEN + "You Gamemode now is "
						+ target.getGameMode() + ", set by "
						+ ChatColor.DARK_PURPLE + player.getName());

				player.sendMessage(ChatColor.GREEN + "You set "
						+ target.getGameMode() + " for "
						+ ChatColor.DARK_PURPLE + target.getName());
			}

		} else if (command.equalsIgnoreCase("gms")
				&& player.hasPermission("sst.gms")) {

			if (args.length == 0) {

				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("Enter " + ChatColor.GOLD
						+ player.getGameMode());
			} else if (args.length == 1 && player.hasPermission("sst.gmso")) {

				Player target = player.getServer().getPlayer(args[0]);

				if (target == null) {

					player.sendMessage("PLAYER " + target + " isn't found");
				}

				target.setGameMode(GameMode.SURVIVAL);

				target.sendMessage(ChatColor.GREEN + "You entered "
						+ target.getGameMode() + " set by "
						+ ChatColor.DARK_PURPLE + player.getName());

				player.sendMessage(ChatColor.GREEN + "Gamemode set to  "
						+ target.getGameMode() + " " + ChatColor.DARK_PURPLE +

						target.getName());
			}

		} else if (command.equalsIgnoreCase("gm")
				&& player.hasPermission("sst.gm")) {

			if (args.length == 0) {

				player.sendMessage("Current GameMode " + ChatColor.GOLD
						+ player.getGameMode());

			} else if (args.length == 1) {

				if (player.hasPermission("sst.gmo")) {

					Player target = Bukkit.getPlayer(args[0]);
					player.sendMessage("Current GameMode of " + ChatColor.GOLD
							+ target.getDisplayName() + " "
							+ target.getGameMode());
				} else if (!player.hasPermission("sst.gmo")) {

					player.sendMessage("You aren't allowed to lookup others gamemode");
				}

			}

		}
		return false;

	}

	public static boolean fly(Player player, String command, String[] args,
			Command cmd) {

		if (command.equalsIgnoreCase("fly") && player.hasPermission("sst.fly")) {

			if (args.length == 0) {

				if (!player.getAllowFlight() && !player.isFlying()) {

					player.setAllowFlight(true);
					player.setFlying(true);
					player.sendMessage("You can now fly ");

				} else if (player.getAllowFlight() && !player.isFlying()) {
					player.setFlying(false);
					player.setAllowFlight(false);
					player.sendMessage("Fly is now disabled");
				}

			} else if (args.length == 1) {

				Player target = Bukkit.getPlayer(args[0]);

				if (player.hasPermission("sst.flyo")) {

					if (!target.getAllowFlight() && !target.isFlying()) {

						target.setAllowFlight(true);
						target.setFlying(true);
						player.sendMessage("Set fly on for " + target.getName());
						target.sendMessage("You can now fly! Allowed by "
								+ player.getDisplayName());

					} else if (target.getAllowFlight() && !target.isFlying()) {

						target.setFlying(false);
						target.setAllowFlight(false);
						player.sendMessage("Set fly off for "
								+ target.getName());
						target.sendMessage("Until now you have to walk on feet! Disallowed by "
								+ player.getDisplayName());
					}
				} else {

					player.sendMessage("No permission for flying others");

				}

			}
		} else if (command.equalsIgnoreCase("fly")
				&& !player.hasPermission("sst.fly")) {

			player.sendMessage("No Permission for flying");

		}

		return false;

	}

}

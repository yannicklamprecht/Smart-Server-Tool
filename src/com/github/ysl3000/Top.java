package com.github.ysl3000;

import java.io.IOException;
import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Top {

	static HashMap<Player, ItemStack[]> invp = new HashMap<Player, ItemStack[]>();
	static HashMap<Player, Location> locp = new HashMap<Player, Location>();

	public static boolean toggleop(Command cmd, CommandSender sender,
			String[] args) throws IOException {

		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("mod")) {

			if (player.hasPermission("sst.mod")) {

				try {
					mod(player, args);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (cmd.getName().equalsIgnoreCase("done")) {
			if (player.hasPermission("sst.mod")) {

				done(player, args);
			}
		}

		else if (cmd.getName().equalsIgnoreCase("/admin")) {

			admin(player, args);
		}

		return false;

	}

	public static boolean done(Player player, String[] args) {

		if (args.length == 0) {

			player.getInventory().clear();

			

			player.getInventory().setContents(invp.get(player));
			
			

			if (!locp.containsKey(player)) {

				Command cmd = new Command("spawn") {

					@Override
					public boolean execute(CommandSender arg0, String arg1,
							String[] arg2) {
						// TODO Auto-generated method stub
						return false;
					}
				};

				try {
					Spawnarea.spawn(player, null, args, cmd);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				player.teleport(locp.get(player));

			}

			player.setOp(false);
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage((ChatColor.RED + "Modmode disabled"));

			return false;

		} else if (args.length == 1) {
			if (player.hasPermission("sst.mod")) {

				Player target = player.getServer().getPlayer(args[0]);

				target.setGameMode(GameMode.SURVIVAL);
				target.getInventory().clear();

				target.getInventory().setContents(invp.get(target));
				target.teleport(locp.get(target));

				player.sendMessage((ChatColor.RED + "modmode disabled for " + target
						.getName()));
				target.sendMessage((ChatColor.RED + "modmode disabled"));

			}

		}

		return false;

	}

	public static boolean mod(Player player, String[] args)throws Exception {

		if (args.length == 0) {

			if (player.getInventory().getContents().length == 0) {

				player.getInventory().addItem(new ItemStack(0, 4));
			}

			invp.put(player, player.getInventory().getContents());

			locp.put(player, player.getLocation());
			player.getInventory().clear();
			player.setOp(true);
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage((ChatColor.GREEN + "Modmode enabled"));

		} else if (args.length == 1) {

			Player target = player.getServer().getPlayer(args[0]);

			invp.put(target, target.getInventory().getContents());
			locp.put(target, target.getLocation());
			target.getInventory().clear();
			target.setGameMode(GameMode.CREATIVE);

			player.sendMessage((ChatColor.GREEN + "modmode enabled for "
					+ ChatColor.GOLD + target.getName()));
			target.sendMessage((ChatColor.GREEN + "modmode enabled"));
		}
		return false;

	}

	public static boolean admin(Player player, String[] args) {

		if (args.length == 0) {
			if (!player.isOp() && ((player.hasPermission("sst.admin")))) {

				player.setOp(true);

				player.sendMessage((ChatColor.GREEN + "Op enabled"));

			} else if (player.isOp() && player.hasPermission("sst.admin")) {

				player.setOp(false);

				player.sendMessage((ChatColor.RED + "Op disabled"));
			}
		}

		else if (args.length == 1) {
			Player target = player.getServer().getPlayer(args[0]);

			if (!target.isOp() && player.hasPermission("sst.admin")) {

				invp.put(target, target.getInventory().getContents());
				locp.put(target, target.getLocation());

				target.setOp(true);
				player.sendMessage((ChatColor.GREEN + "Op enabled for " + target
						.getName()));
				target.sendMessage((ChatColor.GREEN + "Op enabled"));
			} else if (target.isOp() && player.hasPermission("sst.admin")) {

				target.getInventory().addItem(invp.get(target));
				target.teleport(locp.get(target));

				target.setOp(false);
				player.sendMessage((ChatColor.RED + "Op disabled for " + target
						.getName()));
				target.sendMessage((ChatColor.RED + "Op disabled"));
			}
		}
		return false;
	}
}

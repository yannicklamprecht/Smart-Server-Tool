package com.github.ysl3000;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class Spawnarea {

	private static BufferedWriter bw;

	private static FileWriter fw;

	private static FileReader fr;
	private static BufferedReader br;

	private static double x;
	private static double y;
	private static double z;

	public static void setspawn(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		if (command.equalsIgnoreCase("/setsp")
				&& sender.hasPermission("sst.setsp")) {

			String world = ((Player) sender).getWorld().getName();

			if (fw == null) {

				fw = new FileWriter(
						(SmartServerTool.getMainDirectory() + "/spawns/")
								+ world + "Spawn.yml", false);
				bw = new BufferedWriter(fw);

				double x = ((Player) sender).getLocation().getX();
				double y = ((Player) sender).getLocation().getY();
				double z = ((Player) sender).getLocation().getZ();

				sender.sendMessage(ChatColor.AQUA + "Spawn set in "
						+ ChatColor.GOLD + world);

				bw.write(x + ":");
				bw.write(y + ":");
				bw.write(z + ":");

				bw.close();
				fw.close();

			} else {

				fw = new FileWriter(
						(SmartServerTool.getMainDirectory() + "/spawns/")
								+ world + "Spawn.yml", false);
				bw = new BufferedWriter(fw);

				double x = ((Player) sender).getLocation().getX();
				double y = ((Player) sender).getLocation().getY();
				double z = ((Player) sender).getLocation().getZ();

				bw.write(x + ":");
				bw.write(y + ":");
				bw.write(z + ":");

				bw.close();
				fw.close();

			}

		}

	}

	public static void tele(Player player, String command, String[] args)
			throws Exception {

		if (command.equalsIgnoreCase("spawn")
				&& player.hasPermission("sst.spawn")) {

			respawn(player, player.getWorld().getName());

		}

	}

	public static void respawn(Player player, String w) {

		if (player.getBedSpawnLocation() == null) {

			try {

				String world = w;

				fr = new FileReader((SmartServerTool.getMainDirectory())
						+ world + "Spawn.yml");
				br = new BufferedReader(fr);

				String all = br.readLine();

				String[] data = all.split(":");

				x = Double.parseDouble(data[0]);
				y = Double.parseDouble(data[1]);
				z = Double.parseDouble(data[2]);

				Location lc = new Location(player.getWorld(), 1, 2, 3);

				lc.add(x, y, z);
				br.close();
				fr.close();

				player.teleport(lc);
				player.sendMessage("Teleported to Spawn of world "
						+ ChatColor.GOLD + w);
			} catch (Exception e) {

			}

		} else {

			player.teleport(player.getBedSpawnLocation());
		}
	}

}

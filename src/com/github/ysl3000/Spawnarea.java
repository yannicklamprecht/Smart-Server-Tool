package com.github.ysl3000;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileReader;
import java.io.FileWriter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import org.bukkit.World;
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
	private static float pitch;
	private static float yaw;

	private static World wo;
	private static Location lc = new Location(wo, 1, 2, 3, yaw, pitch);
	private static String world;

	public static void spawn(CommandSender sender, String command, String[] args,
			Command cmd) throws Exception {

		
		Player player = (Player) sender;
		if (command.equalsIgnoreCase("/setsp")
				&& sender.hasPermission("sst.setsp")) {
			
			if(sender instanceof Player){
				world = player.getWorld().getName();

				if (fw == null) {

					fw = new FileWriter(
							(SmartServerTool.getMainDirectory() + "/spawns/")
									+ world + "Spawn.yml", false);
					bw = new BufferedWriter(fw);

					double x = player.getLocation().getBlockX();
					double y = player.getLocation().getBlockY();
					double z = player.getLocation().getBlockZ();
					double pitch = player.getLocation().getPitch();
					double yaw = player.getLocation().getYaw();

					player.sendMessage(ChatColor.AQUA + "Spawn set in "
							+ ChatColor.GOLD + world);

					bw.write(world + ":");
					bw.write(x + ":");
					bw.write(y + ":");
					bw.write(z + ":");
					bw.write(pitch + ":");
					bw.write(yaw + ":");

					bw.close();
					fw.close();

				} else {

					fw = new FileWriter(
							(SmartServerTool.getMainDirectory() + "/spawns/")
									+ world + "Spawn.yml", false);
					bw = new BufferedWriter(fw);

					double x = player.getLocation().getBlockX();
					double y = player.getLocation().getBlockY();
					double z = player.getLocation().getBlockZ();
					double pitch = player.getLocation().getPitch();
					double yaw = player.getLocation().getYaw();

					bw.write(world + ":");
					bw.write(x + ":");
					bw.write(y + ":");
					bw.write(z + ":");
					bw.write(pitch + ":");
					bw.write(yaw + ":");

					bw.close();
					fw.close();

				}
			}else{
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

			

		} else if (command.equalsIgnoreCase("spawn")
				&& player.hasPermission("sst.spawn")) {
			
			if(sender instanceof Player){
				String world = player.getWorld().getName();

				fr = new FileReader((SmartServerTool.getMainDirectory())
						+ "/spawns/" + world + "Spawn.yml");
				br = new BufferedReader(fr);

				String all = br.readLine();

				String[] data = all.split(":");

				World wo = Bukkit.getWorld(data[0]);
				x = Double.parseDouble(data[1]);
				y = Double.parseDouble(data[2]);
				z = Double.parseDouble(data[3]);
				pitch = Float.parseFloat(data[4]);
				yaw = Float.parseFloat(data[5]);

				setLc(new Location(wo, x, y, z));
				getLc().setPitch(pitch);
				getLc().setYaw(yaw);

				br.close();
				fr.close();

				player.teleport(getLc());
				player.sendMessage("Teleported to Spawn of world " + ChatColor.GOLD
						+ world);

			}else{
				sender.sendMessage(SmartServerTool.consolehasperformed);
			}

			}

			
	}

	public static Location getLc() {
		return lc;
	}

	public static void setLc(Location loc) {
		lc = loc;
	}

	public static void tospawn(Player player) throws Exception {

		String worldname = player.getWorld().getName();

		fr = new FileReader((SmartServerTool.getMainDirectory()) + "/spawns/"
				+ worldname + "Spawn.yml");
		br = new BufferedReader(fr);

		String all = br.readLine();

		String[] data = all.split(":");

		World wo = player.getWorld();
		x = Double.parseDouble(data[1]);
		y = Double.parseDouble(data[2]);
		z = Double.parseDouble(data[3]);
		pitch = Float.parseFloat(data[4]);
		yaw = Float.parseFloat(data[5]);

		setLc(new Location(wo, x, y, z));
		getLc().setPitch(pitch);
		getLc().setYaw(yaw);

		br.close();
		fr.close();

		player.teleport(getLc());

	}

}

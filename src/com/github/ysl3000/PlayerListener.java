package com.github.ysl3000;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerListener implements Listener {

	private static double x;
	private static double y;
	private static double z;
	private static float pitch;
	private static float yaw;
	private static FileReader fr;
	private static BufferedReader br;
	private static HashMap<Player, Location> LastL = new HashMap<Player, Location>();

	public static SmartServerTool plugin;
	HashMap<String, Integer> login = new HashMap<String, Integer>();

	public static HashMap<Player, Location> getlocation() {

		return LastL;

	}

	public static HashMap<Player, ExperienceOrb> exp = new HashMap<Player, ExperienceOrb>();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws Exception {

		Player player = event.getPlayer();

		Prefix.Pfix(player);

		if (!player.hasPlayedBefore()) {
			event.setJoinMessage("Welcome " + player.getDisplayName()
					+ ChatColor.WHITE + " to " + ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.GOLD + " "
					+ player.getName() + " is playing the first time!");
		} else {

			event.setJoinMessage("Welcome " + player.getDisplayName()
					+ ChatColor.WHITE + " to " + ChatColor.GREEN
					+ Bukkit.getServerName());
		}

	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		event.setQuitMessage(ChatColor.GOLD + player.getName()
				+ ChatColor.WHITE + " has left " + ChatColor.GREEN
				+ Bukkit.getServerName());
		
		

	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {

		Player player = event.getPlayer();

		if (player.getBedSpawnLocation() == null) {

			String world = player.getWorld().getName();

			String all;

			fr = new FileReader((SmartServerTool.getMainDirectory())
					+ "/spawns/" + world + "Spawn.yml");

			br = new BufferedReader(fr);

			all = br.readLine();

			String[] data = all.split(":");

			World wo = Bukkit.getWorld(data[0]);
			x = Double.parseDouble(data[1]);
			y = Double.parseDouble(data[2]);
			z = Double.parseDouble(data[3]);
			pitch = Float.parseFloat(data[4]);
			yaw = Float.parseFloat(data[5]);

			Location lc = new Location(wo, x, y, z, yaw, pitch);

			lc.setWorld(wo);
			lc.setPitch(pitch);
			lc.setYaw(yaw);
			lc.setX(x);
			lc.setY(y);
			lc.setZ(z);

			br.close();
			fr.close();

			player.teleport(lc);

		} else if (player.getBedSpawnLocation() != null) {

			player.teleport(player.getBedSpawnLocation());
		}

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) throws Exception {

		event.setNewExp((int) event.getEntity().getPlayer()
				.getTotalExperience());
	}

}

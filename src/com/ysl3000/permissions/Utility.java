package com.ysl3000.permissions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utility {

	public static void tospawn(Player player) throws Exception {
		player.teleport(player.getWorld().getSpawnLocation());
	}

	public static String getTime(long time, String format) {
		return new SimpleDateFormat(format).format(new Date(time));
	}

	public static String listPlayers() {
		Player ar[] = Bukkit.getOnlinePlayers();
		String liste = "";
		for (Player p : Bukkit.getOnlinePlayers()) {

			if (p.equals(ar[Bukkit.getOnlinePlayers().length - 1])) {

				liste += p.getDisplayName();
			} else {

				liste += p.getDisplayName() + ", ";
			}
		}

		return liste;
	}
}

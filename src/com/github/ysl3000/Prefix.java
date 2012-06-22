package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix {

	private static int i = 0;

	public static void Pfix(Player player) {

		if (player.getName().length() <= 14) {
			nameColor(player);

		} else {
			
			
			player.setPlayerListName(ChatColor.RED+(player.getName().substring(0, 13)));
			
			player.setDisplayName(ChatColor.RED+ player.getName()+ChatColor.WHITE);
		}

	}

	public static void nameColor(Player player) {

		while (i < 13) {

			if (i == 0) {

				player.setDisplayName(ChatColor.RED + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.RED + player.getName());

				i++;
				break;
			} else if (i == 1) {

				player.setDisplayName(ChatColor.AQUA + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.AQUA + player.getName());

				i++;
				break;
			} else if (i == 2) {
				player.setDisplayName(ChatColor.YELLOW + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.YELLOW + player.getName());
				i++;
				break;
			} else if (i == 3) {
				player.setDisplayName(ChatColor.BLUE + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.BLUE + player.getName());
				i++;
				break;
			} else if (i == 4) {
				player.setDisplayName(ChatColor.DARK_AQUA + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.DARK_AQUA + player.getName());
				i++;
				break;
			} else if (i == 5) {
				player.setDisplayName(ChatColor.DARK_BLUE + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.DARK_BLUE + player.getName());
				i++;
				break;
			} else if (i == 6) {
				player.setDisplayName(ChatColor.LIGHT_PURPLE + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.LIGHT_PURPLE
						+ player.getName());
				i++;
				break;
			} else if (i == 7) {
				player.setDisplayName(ChatColor.DARK_GREEN + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.DARK_GREEN
						+ player.getName());
				i++;
				break;
			} else if (i == 8) {
				player.setDisplayName(ChatColor.DARK_PURPLE + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.DARK_PURPLE
						+ player.getName());
				i++;
				break;
			} else if (i == 9) {
				player.setDisplayName(ChatColor.DARK_RED + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.DARK_RED + player.getName());
				i++;
				break;
			} else if (i == 10) {
				player.setDisplayName(ChatColor.GOLD + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.GOLD + player.getName());
				i++;
				break;
			} else if (i == 11) {
				player.setDisplayName(ChatColor.GRAY + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.GRAY + player.getName());
				i++;
				break;
			} else if (i == 12) {
				player.setDisplayName(ChatColor.GREEN + player.getName()
						+ ChatColor.WHITE);
				player.setPlayerListName(ChatColor.GREEN + player.getName());
				i = 0;
				break;
			} 

		}

	}

}

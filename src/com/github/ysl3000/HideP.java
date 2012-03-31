package com.github.ysl3000;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HideP {

	public static SmartServerTool plugin;
	static HashMap<Player, ItemStack[]> pinv = new HashMap<Player, ItemStack[]>();

	public static boolean hide(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		Player player = (Player) sender;
		Player[] playerlist = Bukkit.getOnlinePlayers();

		if (command.equalsIgnoreCase("hide")
				&& sender.hasPermission("sst.visible")) {

			pinv.put(player, player.getInventory().getContents());
			player.getInventory().clear();
			player.sendMessage("You were hidden");

			player.setDisplayName("[h]" + player.getDisplayName());

			player.setPlayerListName("[h]" + player.getName());
			for (int i = 0; i < playerlist.length; i++) {

				if (!playerlist[i].hasPermission("sst.cansee")) {
					playerlist[i].hidePlayer(player);

				} else {
					playerlist[i].setPlayerListName("[h]" + player.getName());
					playerlist[i].showPlayer(player);

				}

			}

		} else if (command.equalsIgnoreCase("show")
				&& player.hasPermission("sst.visible")) {

			Prefix.Pfix(player);
			player.sendMessage("You you are now shown");
			player.getInventory().clear();

			player.getInventory().setContents(pinv.get(player));

			player.setPlayerListName(player.getDisplayName());
			player.setDisplayName(player.getDisplayName());

			for (int i = 0; i < playerlist.length; i++) {

				playerlist[i].showPlayer(player);

			}
		}
		return true;

	}
}

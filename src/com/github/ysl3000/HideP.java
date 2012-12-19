package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HideP {

	public static SmartServerTool plugin;

	public static void hide(CommandSender sender, String command,
			String[] args, Command cmd) throws Exception {

		if (sender instanceof Player) {

			if ((Commands.getHide(command))
					&& Permission.hasvisible((Player)sender)) {

				Player player = (Player) sender;

				player.sendMessage("You were hidden");
				HashmapHandler.setHiddenStatus(player, true);

				runHide(player);

			} else if (Commands.getShow(command)
					&& Permission.hasvisible((Player)sender)) {

				((Player) sender).sendMessage("You you are now shown");

				HashmapHandler.setHiddenStatus((Player) sender, false);
				runHide((Player) sender);
			}
		}
	}

	public static void runHide() {

		for (Player ps : Bukkit.getOnlinePlayers()) {
			runHide(ps);
		}
	}

	public static void runHide(Player p) {

		if (HashmapHandler.isHiddenStatus(p)) {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				if (!Permission.hascansee(pl)) {
					pl.hidePlayer(p);
				}

			}
		} else {
			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(p);
			}

		}

	}

}

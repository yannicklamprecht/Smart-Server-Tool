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

			if ((SmartServerTool.getCommands().getHide(command))
					&& SmartServerTool.getPermission().hasvisible(
							(Player) sender)) {

				Player player = (Player) sender;

				player.sendMessage("You were hidden");
				SmartServerTool.getHSP().setHiddenStatus(player, true);

				runHide(player);

			} else if (SmartServerTool.getCommands().getShow(command)
					&& SmartServerTool.getPermission().hasvisible(
							(Player) sender)) {

				((Player) sender).sendMessage("You you are now shown");

				SmartServerTool.getHSP()
						.setHiddenStatus((Player) sender, false);
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
		for (Player pl : Bukkit.getOnlinePlayers()) {
			if (SmartServerTool.getHSP().isHiddenStatus(p)) {
				if (!SmartServerTool.getPermission().hascansee(pl)) {
					p.hidePlayer(pl);
				}
			} else {
				p.showPlayer(pl);
				p.sendMessage("Player" + pl.getCustomName()
						+ " sieht sie nun wieder");
			}
		}
	}
}

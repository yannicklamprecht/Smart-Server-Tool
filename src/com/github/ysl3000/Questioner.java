package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Questioner {

	public static void quest(Player player, String commandLabel, String[] args,
			Command cmd) {

		if (SmartServerTool.getCommands().getAnswer(commandLabel) && SmartServerTool.getPermission().hasAnswerQ(player)) {
			if (args.length == 0) {
				player.sendMessage("Use /answer <answer>  to unlock your rights");
			} else if (args.length == 1) {

				if (args[0].equalsIgnoreCase(SmartServerTool.getCFGL()
						.getAnswer())) {

					try {

						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"manuadd " + player.getName() + " "
										+ SmartServerTool.getCFGL().getDGN());
					} catch (Exception e) {

						Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
								"pex user " + player.getName() + " group set "
										+ SmartServerTool.getCFGL().getDGN());

					}
				}
			}
		}

	}

	public static void ask(Player player) {

		player.sendMessage(ChatColor.GREEN + "[Question]" + ChatColor.WHITE
				+ SmartServerTool.getCFGL().getQuestion());
	}
}

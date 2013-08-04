package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class Questioner implements CommandExecutor {


	public static void ask(Player player) {

		player.sendMessage(ChatColor.GREEN + "[Question]" + ChatColor.WHITE
				+ SmartServerTool.getCFGL().getQuestion());
	}
	
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission())) {
			player.sendMessage(SmartServerTool.noperms);

			return true;
		}

		if (args.length == 0) {
			player.sendMessage("Use /answer <answer>  to unlock your rights");
		} else if (args.length == 1) {

			if (args[0].equalsIgnoreCase(SmartServerTool.getCFGL().getAnswer())) {

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

		return true;
	}
}

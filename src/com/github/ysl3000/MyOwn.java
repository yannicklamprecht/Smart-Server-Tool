package com.github.ysl3000;

import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MyOwn extends JavaPlugin {

	public static MyOwn plugin;
	public final Logger logger = Logger.getLogger("Minecraft");

	CommandSender sender;
	Command cmd;
	String commandLabel;
	String[] args;

	Player player = (Player) sender;
	Logger log;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("MyOwn");

	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		readCommand((Player) sender, commandLabel);
		
		if (cmd.getName().equalsIgnoreCase("admin")) {

			if (!sender.isOp() && sender.hasPermission("MyOwn.admin")) {
				sender.setOp(true);
				sender.sendMessage((ChatColor.GREEN + "Op enabled"));
			} else if (sender.isOp() && sender.hasPermission("MyOwn.admin")) {
				sender.setOp(false);
				sender.sendMessage((ChatColor.RED + "Op disabled"));
			}

			return true;
		}

		return false;

	}

	public void readCommand(Player player, String command) {
		if (command.equalsIgnoreCase("gmc")
				&& player.hasPermission("MyOwn.gmc")) {

			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage("Enter " + ChatColor.GOLD + "Creative mode");

		}

		else if (command.equalsIgnoreCase("gms")
				&& player.hasPermission("MyOwn.gms")) {

			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage("Enter " + ChatColor.GOLD + "Survival mode");

		} else if (command.equalsIgnoreCase("gm")
				&& player.hasPermission("MyOwn.gm")) {

			player.sendMessage("Current GameMode " + ChatColor.GOLD
					+ player.getGameMode());

		}

	}

}

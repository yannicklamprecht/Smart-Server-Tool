package com.github.ysl3000;

import java.io.IOException;
import java.util.logging.Logger;
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
		log.info("Enable MyOwn");

	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disable MyOwn");

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		try {
			Gm.toggleGm((Player) sender, commandLabel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Top.toggleop(cmd, sender);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		try {
			Time.setTime((Player) sender, commandLabel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			Weather.changeWeather((Player) sender, commandLabel);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}

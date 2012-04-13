package com.github.ysl3000;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SmartServerTool extends JavaPlugin {

	public static SmartServerTool plugin;
	public final static Logger logger = Logger.getLogger("Minecraft");

	CommandSender sender;
	Command cmd;
	String commandLabel;
	private static String mainDirectory = "plugins/SmartServerTool/";
	Logger log;
	Location spawn;

	public static Configuration config;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");

		new File(mainDirectory).mkdir();
		new File(mainDirectory + "/spawns/").mkdir();
		new File(mainDirectory + "/warps/").mkdir();
		new File(mainDirectory + "/CommandLog/").mkdir();

		
		getServer().getPluginManager().registerEvents(new MOTD(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this),
				this);

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		try {
			Gm.toggleGm((Player) sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Top.toggleop(cmd, sender, args);
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

		try {
			Health.kill(sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Info.infos(sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Teleport.tp((Player) sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Spawnarea.spawn((Player) sender, commandLabel, args, cmd);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			CommandLogger
					.commandToLog((Player) sender, commandLabel, args, cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			HideP.hide(sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clearinv((Player) sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public static String getMainDirectory() {

		return mainDirectory;
	}

	public static boolean clearinv(Player player, String command,
			String[] args, Command cmd) throws Exception {

		boolean succes = false;
		if (command.equalsIgnoreCase("ci") && player.hasPermission("sst.ci")) {

			player.getInventory().clear();
			return false;

		} else if (command.equalsIgnoreCase("ci")
				&& !player.hasPermission("sst.ci")) {

			return true;

		}

		return succes;

	}

}

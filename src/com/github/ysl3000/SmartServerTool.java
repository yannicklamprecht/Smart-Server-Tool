package com.github.ysl3000;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SmartServerTool extends JavaPlugin {

	public final PlayerListener pl = new PlayerListener();
	public static SmartServerTool plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	CommandSender sender;
	Command cmd;
	String commandLabel;
	String[] args;
	static String mainDirectory = "plugins/SmartServerTool";
	File file = new File( mainDirectory + File.separator + "spawn.yml" );

	Player player = (Player) sender;
	Logger log;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Enable Smart Server Tool");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);
		new File( mainDirectory ).mkdir();

	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disable Smart Server Tool");

	}

	public void onPlayerJoin(PlayerJoinEvent j) {

	}

	public void onPlayerLeft(PlayerQuitEvent q) {

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
			Spawnarea.setspawn(sender, commandLabel, args, cmd);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Spawnarea.setspawn(sender, commandLabel, args, cmd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Spawnarea.teleportspawn((Player) sender, commandLabel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

}

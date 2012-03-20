package com.github.ysl3000;

import java.io.File;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Location;
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
	public final static Logger logger = Logger.getLogger("Minecraft");
	
	
	
		
	
	
	CommandSender sender;
	Command cmd;
	String commandLabel;
	
	

	private static String mainDirectory = "plugins/SmartServerTool/";

	Logger log;

	Location spawn;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(this.pl, this);

		new File(mainDirectory).mkdir();
		new File(mainDirectory+"/spawns/").mkdir();
		new File(mainDirectory+"/warps/").mkdir();
		new File(mainDirectory+"/CommandLog/").mkdir();

		
		
		
	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");

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
			Spawnarea.tele((Player) sender, commandLabel, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			CommandLogger.commandToLog((Player)sender, commandLabel, args, cmd);
		}catch(Exception e){
			e.printStackTrace();
		}

		return true;

	}

	public static String getMainDirectory() {

		return mainDirectory;
	}
}

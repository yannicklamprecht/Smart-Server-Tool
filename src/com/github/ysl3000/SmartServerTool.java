package com.github.ysl3000;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

	public static String consolehasperformed = "Only Player can perform this command";

	public static Configuration config;
	public static int inc = 0;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");

		new File(mainDirectory).mkdir();
		new File(mainDirectory + "/spawns/").mkdir();
		new File(mainDirectory + "/CommandLog/").mkdir();

		getServer().getPluginManager().registerEvents(new MOTD(this), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(this),
				this);

		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		if (MOTD.getadvert()) {

			Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
					new Runnable() {

						@Override
						public void run() {

							try {

								for (Player p : Bukkit.getOnlinePlayers()) {

									p.sendMessage(ChatColor.RED
											+ "[Plugin-Advert]"
											+ ChatColor.GOLD
											+ "Plugin-Hompage: www.SmartServerTool.de");
								}

							} catch (Exception e) {

							}

						}
					}, 0, 300 * 20L);
		}

	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");

	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (sender instanceof Player) {
			try {
				Gm.toggleGm((Player) sender, commandLabel, args, cmd);
				Top.toggleop(cmd, sender, args);
				Time.setTime((Player) sender, commandLabel);
				Weather.changeWeather((Player) sender, commandLabel);
				Health.kill(sender, commandLabel, args, cmd);
				Info.infos(sender, commandLabel, args, cmd);
				Teleport.tp((Player) sender, commandLabel, args, cmd);
				Spawnarea.spawn(sender, commandLabel, args, cmd);
				CommandLogger.commandToLog(sender, commandLabel, args, cmd);
				HideP.hide(sender, commandLabel, args, cmd);
				ItemMan.item((Player) sender, commandLabel, args, cmd);
				KickManager.kick(sender, commandLabel, args, cmd);

			} catch (Exception e) {
			}

		}

		return true;

	}

	public static String getMainDirectory() {

		return mainDirectory;
	}

}

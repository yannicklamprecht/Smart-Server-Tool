package com.github.ysl3000;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ysl3000.Commands.Commander;
import com.github.ysl3000.Commands.Commands;
import com.github.ysl3000.Event.BlockListener;
import com.github.ysl3000.Event.ChestProtectionListener;
import com.github.ysl3000.Event.EntityListener;
import com.github.ysl3000.Event.PlayerListener;
import com.github.ysl3000.Event.SignListener;
import com.github.ysl3000.Prefixer.MOTD;
import com.github.ysl3000.Utils.ConfigLoader;
import com.github.ysl3000.Utils.DateTime;
import com.github.ysl3000.Utils.HashmapHandler;
import com.github.ysl3000.Utils.Permission;
import com.github.ysl3000.Utils.RecipeConfigloader;
import com.github.ysl3000.Utils.Recipes;

public class SmartServerTool extends JavaPlugin {

	public static SmartServerTool plugin;
	public final static Logger logger = Logger.getLogger("Minecraft");

	CommandSender sender;
	Command cmd;
	String commandLabel;
	private static String mainDirectory = "plugins/SmartServerTool/";
	Logger log;

	public static String noperms = ChatColor.RED
			+ "You don't have the permission to perform this ...";
	public static String consolehasperformed = "Only Player can perform this command";

	protected FileConfiguration config = null;
	protected FileConfiguration spawnConfig = null;
	protected File spawnConfigFile = null;
	protected FileConfiguration recipeConfig = null;
	protected File recipeConfigFile = null;

	private static HashmapHandler hsp;
	private static ConfigLoader cfgl;
	private static Commands cmds;
	private static Permission perms;
	private static DateTime dateTime;
	private static Commander commander;

	public void onEnable() {

		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");

		new File(mainDirectory).mkdir();
		new File(mainDirectory + "/CommandLog/").mkdir();

		new PlayerListener(this);
		new BlockListener(this);
		new EntityListener(this);
		new SignListener(this);
		new ChestProtectionListener(this);
		new MOTD(this);
		cfgl = new ConfigLoader(this);
		new RecipeConfigloader(this);
		hsp = new HashmapHandler(this);
		cmds = new Commands();
		perms = new Permission();
		dateTime = new DateTime();
		setCommander(new Commander());

		config = this.getConfig();
		spawnConfig = this.getSpawnConfig();
		recipeConfig = this.getRecipeConfig();

		this.getConfig().options().copyDefaults(true);
		this.getSpawnConfig().options().copyDefaults(true);
		this.getRecipeConfig().options().copyDefaults(true);

		this.saveConfig();
		this.saveSpawnConfig();
		this.saveRecipeConfig();
		new Recipes(this);
		Bukkit.setSpawnRadius(0);

		for (World i : Bukkit.getWorlds()) {

			if (this.getSpawnConfig().getDouble(i.getName() + ".x") == 0.0
					&& this.getSpawnConfig().getDouble(i.getName() + ".y") == 0.0
					&& this.getSpawnConfig().getDouble(i.getName() + ".z") == 0.0) {

				i.setSpawnLocation(
						(int) this.getSpawnConfig().getDouble(
								i.getName() + ".x"), i.getHighestBlockYAt(
								(int) this.getSpawnConfig().getDouble(
										i.getName() + ".x"),
								(int) this.getSpawnConfig().getDouble(
										i.getName() + ".z")), (int) this
								.getSpawnConfig().getDouble(i.getName() + ".z"));

			} else {
				try {
					i.setSpawnLocation(
							(int) this.getSpawnConfig().getDouble(
									i.getName() + ".x"),
							(int) this.getSpawnConfig().getDouble(
									i.getName() + ".y"),
							(int) this.getSpawnConfig().getDouble(
									i.getName() + ".z"));
				} catch (Exception eN) {

				}
			}
		}
		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " is enabled");
		if (getCFGL().getadvert()) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
					new Runnable() {
						@Override
						public void run() {
							try {
								for (Player p : Bukkit.getOnlinePlayers()) {
									p.sendMessage(ChatColor.RED
											+ getCFGL().getAdvertPrefix() + " "
											+ ChatColor.GREEN
											+ getCFGL().getAdvertMessage());
								}
							} catch (Exception e) {
							}
						}
					}, 0, getCFGL().getAdvertTime() * 20L);
		}

		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {

				for (World i : Bukkit.getWorlds()) {

					getSpawnConfig().set(
							i.getName() + ".x",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getX()));
					getSpawnConfig().set(
							i.getName() + ".y",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getY()));
					getSpawnConfig().set(
							i.getName() + ".z",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getZ()));

					i.save();

				}

				Bukkit.savePlayers();

				try {
					saveConfig();
					saveSpawnConfig();
				} catch (Exception e) {
					log.warning("Config failed saving!");
				}

			}
		}, 50, getCFGL().getSaveTimeInterval() * 20L);
	}

	public void onReload() {
		plugin.getConfig();
		plugin.getSpawnConfig();
		plugin.getRecipeConfig();
	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");
		saveConfig();
		saveSpawnConfig();
		saveRecipeConfig();
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (sender instanceof Player) {

			getCommander().runCommands(sender, cmd, commandLabel, args);

		} else if (sender instanceof ConsoleCommandSender) {
			sender.sendMessage(consolehasperformed);
		}

		return true;

	}

	public static String getMainDirectory() {

		return mainDirectory;
	}

	public void reloadSpawnConfig() {
		if (spawnConfigFile == null) {
			spawnConfigFile = new File(SmartServerTool.getMainDirectory(),
					"spawn.yml");
		}
		spawnConfig = YamlConfiguration.loadConfiguration(spawnConfigFile);

		InputStream defConfigStream = getResource("spawn.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			spawnConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getSpawnConfig() {
		if (spawnConfig == null) {
			reloadSpawnConfig();
		}
		return spawnConfig;
	}

	public void saveSpawnConfig() {
		if (spawnConfig == null || spawnConfigFile == null) {
			return;
		}
		try {
			spawnConfig.save(spawnConfigFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + spawnConfigFile, ex);
		}
	}

	public FileConfiguration getRecipeConfig() {
		if (recipeConfig == null) {
			reloadRecipeConfig();
		}
		return recipeConfig;
	}

	public void saveRecipeConfig() {
		if (recipeConfig == null || recipeConfigFile == null) {
			return;
		}
		try {
			recipeConfig.save(recipeConfigFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + recipeConfigFile, ex);
		}
	}

	public void reloadRecipeConfig() {
		if (recipeConfigFile == null) {
			recipeConfigFile = new File(SmartServerTool.getMainDirectory(),
					"recipe.yml");
		}
		recipeConfig = YamlConfiguration.loadConfiguration(recipeConfigFile);

		InputStream defConfigStream = getResource("recipe.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			recipeConfig.setDefaults(defConfig);
		}
	}

	public static HashmapHandler getHSP() {
		return hsp;
	}

	public static ConfigLoader getCFGL() {
		return cfgl;
	}

	public static Commands getCommands() {
		return cmds;
	}

	public static Permission getPermission() {
		return perms;
	}

	public static DateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @return the commander
	 */
	public static Commander getCommander() {
		return commander;
	}

	/**
	 * @param commander
	 *            the commander to set
	 */
	public static void setCommander(Commander commander) {
		SmartServerTool.commander = commander;
	}
}

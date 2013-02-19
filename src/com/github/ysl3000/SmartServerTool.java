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
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

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

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");

		new File(mainDirectory).mkdir();
		new File(mainDirectory + "/CommandLog/").mkdir();

		new PlayerListener(this);
		new BlockListener(this);
		new EntityListener(this);
		new MOTD(this);
		new ConfigLoader(this);
		new RecipeConfigloader(this);
		new HashmapHandler(this);

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
		if (ConfigLoader.getadvert()) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
					new Runnable() {
						@Override
						public void run() {
							try {
								for (Player p : Bukkit.getOnlinePlayers()) {
									p.sendMessage(ChatColor.RED
											+ ConfigLoader.getAdvertPrefix()
											+ " " + ChatColor.GREEN
											+ ConfigLoader.getAdvertMessage());
								}
							} catch (Exception e) {
							}
						}
					}, 0, ConfigLoader.getAdvertTime() * 20L);
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
		}, 50, ConfigLoader.getSaveTimeInterval() * 20L);
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
			try {
				Gm.toggleGm((Player) sender, commandLabel, args, cmd);
				Top.toggleop(cmd, sender, args);
				Time.setTime((Player) sender, commandLabel);
				Weather.changeWeather((Player) sender, commandLabel);
				Health.kill(sender, commandLabel, args, cmd);
				Info.infos(sender, commandLabel, args, cmd);
				Teleport.tp((Player) sender, commandLabel, args, cmd);
				SpawnArea.spawn(sender, commandLabel, args, cmd);
				CommandLogger.commandToLog(sender, commandLabel, args, cmd);
				HideP.hide(sender, commandLabel, args, cmd);
				ItemMan.item((Player) sender, commandLabel, args, cmd);
				KickManager.kick(sender, commandLabel, args, cmd);
				EntityManager.removeEntity(sender, commandLabel, args, cmd);
				Inviter.invite(sender, commandLabel, args, cmd);
				Questioner.quest((Player) sender, commandLabel, args, cmd);
				Gm.playerSpeed(sender, commandLabel, args, cmd);
				Gm.godmode(sender, commandLabel, args, cmd);
				ChannelChat.ManageChannel(sender, commandLabel, args, cmd);
				NickName.Nick(sender, commandLabel, args, cmd);
				SSTH.help(sender, commandLabel, args, cmd);
			} catch (Exception e) {
			}

		} else {
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
}

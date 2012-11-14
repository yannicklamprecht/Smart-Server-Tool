package com.github.ysl3000;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
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

	public static String consolehasperformed = "Only Player can perform this command";

	protected FileConfiguration config;
	protected FileConfiguration customConfig = null;
	protected File customConfigFile = null;
	protected FileConfiguration inviteConfig = null;
	protected File inviteConfigFile = null;

	public void onEnable() {
		log = Logger.getLogger("Minecraft");
		log.info("Smart Server Tool enabled");

		MOTD.setIsMOD(new HashMap<Player, Boolean>());
		new File(mainDirectory).mkdir();
		new File(mainDirectory + "/CommandLog/").mkdir();

		new PlayerListener(this);
		new MOTD(this);
		new ConfigLoader(this);

		config = getConfig();
		getCustomConfig();
		getInviteConfig();
		this.getConfig().options().copyDefaults(true);

		this.getInviteConfig().options().copyDefaults(true);
		this.getCustomConfig().options().copyDefaults(true);

		this.saveConfig();
		this.saveCustomConfig();
		this.saveInviteConfig();

		Bukkit.setSpawnRadius(0);

		for (World i : Bukkit.getWorlds()) {

			i.setSpawnLocation(
					(int) this.getCustomConfig().getDouble(i.getName() + ".x"),
					(int) this.getCustomConfig().getDouble(i.getName() + ".y"),
					(int) this.getCustomConfig().getDouble(i.getName() + ".z"));

		}

		PluginDescriptionFile pdf = this.getDescription();
		log.info(pdf.getName() + " version " + pdf.getVersion() + " is enabled");

		ShapedRecipe sr = new ShapedRecipe(new ItemStack(
				Material.ENCHANTMENT_TABLE, 1));
		sr.shape(new String[] { "   ", " b ", "www" })
				.setIngredient('b', Material.BOOKSHELF)
				.setIngredient('w', Material.WOOD);

		getServer().addRecipe(sr);

		if (ConfigLoader.getadvert()) {

			Bukkit.getScheduler().scheduleSyncRepeatingTask(this,
					new Runnable() {

						@Override
						public void run() {

							try {

								for (Player p : Bukkit.getOnlinePlayers()) {

									p.sendMessage(ChatColor.RED
											+ "[Plugin-Advert]"
											+ ChatColor.GOLD
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

					getCustomConfig().set(
							i.getName() + ".x",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getX()));
					getCustomConfig().set(
							i.getName() + ".y",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getY()));
					getCustomConfig().set(
							i.getName() + ".z",
							Double.valueOf(Bukkit.getWorld(i.getName())
									.getSpawnLocation().getZ()));

					i.save();

				}

				Bukkit.savePlayers();

				try {

					Random rando = new Random();
					if (rando.nextInt(100) == 1) {
						log.info("Saved");
					}
					saveConfig();
					saveCustomConfig();

				} catch (Exception e) {
					log.warning("Config failed saving!");
				}

			}
		}, 50, ConfigLoader.getSaveTimeInterval() * 20L);

	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");
		saveConfig();
		saveCustomConfig();

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
				EntityListener.removeEntity(sender, commandLabel, args, cmd);
				Inviter.invite(sender, commandLabel, args, cmd);
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

	public void reloadInviteConfig(){
		if (inviteConfigFile == null) {
			inviteConfigFile = new File(SmartServerTool.getMainDirectory(),
					"invite.yml");
		}
		inviteConfig = YamlConfiguration.loadConfiguration(inviteConfigFile);

		// Look for defaults in the jar
		InputStream defConfigStream = getResource("invite.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			inviteConfig.setDefaults(defConfig);
		}
	}
	public void reloadCustomConfig() {
		if (customConfigFile == null) {
			customConfigFile = new File(SmartServerTool.getMainDirectory(),
					"spawn.yml");
		}
		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

		// Look for defaults in the jar
		InputStream defConfigStream = getResource("spawn.yml");
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration
					.loadConfiguration(defConfigStream);
			customConfig.setDefaults(defConfig);
		}
	}
	public FileConfiguration getInviteConfig() {
		if (inviteConfig == null) {
			reloadInviteConfig();
		}
		return inviteConfig;
	}

	
	public FileConfiguration getCustomConfig() {
		if (customConfig == null) {
			reloadCustomConfig();
		}
		return customConfig;
	}

	public void saveInviteConfig() {
		if (inviteConfig == null || inviteConfigFile == null) {
			return;
		}
		try {
			inviteConfig.save(inviteConfigFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + inviteConfigFile, ex);
		}
	}
	public void saveCustomConfig() {
		if (customConfig == null || customConfigFile == null) {
			return;
		}
		try {
			customConfig.save(customConfigFile);
		} catch (IOException ex) {
			Logger.getLogger(JavaPlugin.class.getName()).log(Level.SEVERE,
					"Could not save config to " + customConfigFile, ex);
		}
	}

}

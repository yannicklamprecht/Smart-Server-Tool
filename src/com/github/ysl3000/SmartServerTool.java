package com.github.ysl3000;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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

import com.github.ysl3000.Event.BlockListener;
import com.github.ysl3000.Event.ChestProtectionListener;
import com.github.ysl3000.Event.EntityListener;
import com.github.ysl3000.Event.PlayerListener;
import com.github.ysl3000.Event.SignListener;
import com.github.ysl3000.Prefixer.MOTD;
import com.github.ysl3000.Utils.ConfigLoader;
import com.github.ysl3000.Utils.DateTime;
import com.github.ysl3000.Utils.HashmapHandler;
import com.github.ysl3000.Utils.RecipeConfigloader;
import com.github.ysl3000.Utils.Recipes;
import com.ysl3000.cmdexe.Admin;
import com.ysl3000.cmdexe.BackCommand;
import com.ysl3000.cmdexe.ChannelChat;
import com.ysl3000.cmdexe.CheckCurrentGamemode;
import com.ysl3000.cmdexe.ClearInventory;
import com.ysl3000.cmdexe.CreativeGamemode;
import com.ysl3000.cmdexe.DoneCommand;
import com.ysl3000.cmdexe.EntityManager;
import com.ysl3000.cmdexe.FlyMode;
import com.ysl3000.cmdexe.FlySpeedCommand;
import com.ysl3000.cmdexe.FreezeCommand;
import com.ysl3000.cmdexe.GetRealTimeCommand;
import com.ysl3000.cmdexe.Help;
import com.ysl3000.cmdexe.HomeCommand;
import com.ysl3000.cmdexe.Inviter;
import com.ysl3000.cmdexe.KickManager;
import com.ysl3000.cmdexe.ModCommand;
import com.ysl3000.cmdexe.NickName;
import com.ysl3000.cmdexe.OnlinePlayersCommand;
import com.ysl3000.cmdexe.PlayerHeal;
import com.ysl3000.cmdexe.PlayerHealMe;
import com.ysl3000.cmdexe.PlayerKillCommand;
import com.ysl3000.cmdexe.PlayerKillMeCommand;
import com.ysl3000.cmdexe.PlayerLastSeen;
import com.ysl3000.cmdexe.PlayerLookupIpCommand;
import com.ysl3000.cmdexe.Questioner;
import com.ysl3000.cmdexe.RepairItem;
import com.ysl3000.cmdexe.ServerInfo;
import com.ysl3000.cmdexe.SetSpawnCommand;
import com.ysl3000.cmdexe.SkullHeadCommand;
import com.ysl3000.cmdexe.SpawnCommand;
import com.ysl3000.cmdexe.SurvivalGamemode;
import com.ysl3000.cmdexe.SwitchCommand;
import com.ysl3000.cmdexe.TimeDay;
import com.ysl3000.cmdexe.TimeNight;
import com.ysl3000.cmdexe.ToggleGodModeCommand;
import com.ysl3000.cmdexe.WalkSpeedCommand;
import com.ysl3000.cmdexe.WeatherLookup;
import com.ysl3000.cmdexe.WeatherStorm;
import com.ysl3000.cmdexe.WeatherSun;

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
	private static DateTime dateTime;

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
		dateTime = new DateTime();
		if (Bukkit.getServer().getOnlinePlayers().length > 0) {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				SmartServerTool.getHSP().setFlyStatus(p, false);
				SmartServerTool.getHSP().setChannel(p.getName(), "g");
				p.setSleepingIgnored(SmartServerTool.getCFGL()
						.isSleepingIgnored());
			}
		}

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
		SmartServerTool.plugin.getConfig();
		SmartServerTool.plugin.getSpawnConfig();
		SmartServerTool.plugin.getRecipeConfig();

		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			SmartServerTool.getHSP().setFlyStatus(p, false);
			SmartServerTool.getHSP().setChannel(p.getName(), "g");
			p.setSleepingIgnored(SmartServerTool.getCFGL().isSleepingIgnored());
		}
	}

	public void onDisable() {

		log = Logger.getLogger("Minecraft");
		log.info("Disabled Smart Server Tool");
	}

	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(consolehasperformed);
		}

		getCommand("/admin").setExecutor(new Admin());
		getCommand("back").setExecutor(new BackCommand());
		getCommand("gm").setExecutor(new CheckCurrentGamemode());
		getCommand("gmc").setExecutor(new CreativeGamemode());
		getCommand("done").setExecutor(new DoneCommand());
		getCommand("fly").setExecutor(new FlyMode());
		getCommand("fs").setExecutor(new FlySpeedCommand());
		getCommand("freeze").setExecutor(new FreezeCommand());
		getCommand("rt").setExecutor(new GetRealTimeCommand());
		getCommand("home").setExecutor(new HomeCommand());
		getCommand("mod").setExecutor(new ModCommand());
		getCommand("online").setExecutor(new OnlinePlayersCommand());
		getCommand("heal").setExecutor(new PlayerHeal());
		getCommand("healme").setExecutor(new PlayerHealMe());
		getCommand("kill").setExecutor(new PlayerKillCommand());
		getCommand("km").setExecutor(new PlayerKillMeCommand());
		getCommand("seen").setExecutor(new PlayerLastSeen());
		getCommand("/ip").setExecutor(new PlayerLookupIpCommand());
		getCommand("info").setExecutor(new ServerInfo());
		getCommand("setsp").setExecutor(new SetSpawnCommand());
		getCommand("spawn").setExecutor(new SpawnCommand());
		getCommand("gms").setExecutor(new SurvivalGamemode());
		getCommand("switch").setExecutor(new SwitchCommand());
		getCommand("td").setExecutor(new TimeDay());
		getCommand("tn").setExecutor(new TimeNight());
		getCommand("god").setExecutor(new ToggleGodModeCommand());
		getCommand("ws").setExecutor(new WalkSpeedCommand());
		getCommand("wg").setExecutor(new WeatherLookup());
		getCommand("storm").setExecutor(new WeatherStorm());
		getCommand("sun").setExecutor(new WeatherSun());
		getCommand("ssth").setExecutor(new Help());
		getCommand("al").setExecutor(new EntityManager());
		getCommand("jc").setExecutor(new ChannelChat());
		getCommand("invite").setExecutor(new Inviter());
		getCommand("kick").setExecutor(new KickManager());
		getCommand("answer").setExecutor(new Questioner());
		getCommand("nick").setExecutor(new NickName());
		getCommand("ci").setExecutor(new ClearInventory());
		getCommand("i").setExecutor(new SkullHeadCommand());
		getCommand("repair").setExecutor(new RepairItem());
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

	public static DateTime getDateTime() {
		return dateTime;
	}

	public static void openUrl(String url) throws Exception {

		if (Desktop.isDesktopSupported()) {
			URI urls = URI.create(url);
			Desktop.getDesktop().browse(urls);
		}
	}
}

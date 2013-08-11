package com.ysl3000.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.cmdexe.Admin;
import com.ysl3000.cmdexe.BackCommand;
import com.ysl3000.cmdexe.CheckCurrentGamemode;
import com.ysl3000.cmdexe.CreativeGamemode;
import com.ysl3000.cmdexe.DoneCommand;
import com.ysl3000.cmdexe.FlyMode;
import com.ysl3000.cmdexe.FlySpeedCommand;
import com.ysl3000.cmdexe.FreezeCommand;
import com.ysl3000.cmdexe.GetRealTimeCommand;
import com.ysl3000.cmdexe.HomeCommand;
import com.ysl3000.cmdexe.ModCommand;
import com.ysl3000.cmdexe.OnlinePlayersCommand;
import com.ysl3000.cmdexe.PlayerHeal;
import com.ysl3000.cmdexe.PlayerHealMe;
import com.ysl3000.cmdexe.PlayerKillCommand;
import com.ysl3000.cmdexe.PlayerKillMeCommand;
import com.ysl3000.cmdexe.PlayerLastSeen;
import com.ysl3000.cmdexe.PlayerLookupIpCommand;
import com.ysl3000.cmdexe.ServerInfo;
import com.ysl3000.cmdexe.SetSpawnCommand;
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
import com.ysl3000.events.BlockListener;
import com.ysl3000.events.ChestProtectionListener;
import com.ysl3000.events.EntityListener;
import com.ysl3000.events.MOTD;
import com.ysl3000.events.PlayerListener;
import com.ysl3000.events.SignListener;
import com.ysl3000.utils.ConfigLoader;
import com.ysl3000.utils.SmartController;

public class SmartServerTool extends JavaPlugin {

	protected FileConfiguration config = null;
	protected FileConfiguration spawnConfig = null;
	protected File spawnConfigFile = null;
	protected FileConfiguration recipeConfig = null;
	protected File recipeConfigFile = null;

	private static SmartServerTool plugin;

	public static final String mainDirectory = "plugins/SmartServerTool/";
	private static ConfigLoader cfg;

	@Override
	public void onEnable() {

		plugin = new SmartServerTool();
		new File(mainDirectory).mkdir();
		
		SmartController.getSmartControler();

		this.config = this.getConfig();
		this.recipeConfig = this.getRecipeConfig();
		this.spawnConfig = this.getSpawnConfig();

		this.getConfig().options().copyDefaults(true);
		this.getRecipeConfig().options().copyDefaults(true);
		this.getSpawnConfig().options().copyDefaults(true);

		this.saveConfig();
		this.saveRecipeConfig();
		this.saveSpawnConfig();
		cfg = new ConfigLoader(this);

		registerEvents();
	}

	@Override
	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

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
		return true;
	}

	public static SmartServerTool getPlugin() {
		return plugin;
	}

	private void registerEvents() {
		new BlockListener(this);
		new ChestProtectionListener(this);
		new EntityListener(this);
		new PlayerListener(this);
		new SignListener(this);
		new MOTD(this);

	}

	public void reloadSpawnConfig() {
		if (spawnConfigFile == null) {
			spawnConfigFile = new File(SmartServerTool.mainDirectory,
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
			recipeConfigFile = new File(SmartServerTool.mainDirectory,
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
	
	public static ConfigLoader getConfigLoader(){
		return cfg;
	}

}
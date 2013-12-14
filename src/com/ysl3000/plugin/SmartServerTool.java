package com.ysl3000.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.commands.AdminCommand;
import com.ysl3000.commands.BackCommand;
import com.ysl3000.commands.CheckCurrentGamemode;
import com.ysl3000.commands.CreativeGamemode;
import com.ysl3000.commands.DoneCommand;
import com.ysl3000.commands.FlyMode;
import com.ysl3000.commands.FlySpeed;
import com.ysl3000.commands.Freeze;
import com.ysl3000.commands.GetWeather;
import com.ysl3000.commands.God;
import com.ysl3000.commands.Heal;
import com.ysl3000.commands.HealMe;
import com.ysl3000.commands.Home;
import com.ysl3000.commands.Kill;
import com.ysl3000.commands.KillMe;
import com.ysl3000.commands.ModCommand;
import com.ysl3000.commands.Online;
import com.ysl3000.commands.PlayerLookUpIp;
import com.ysl3000.commands.RealTime;
import com.ysl3000.commands.Seen;
import com.ysl3000.commands.ServerInfo;
import com.ysl3000.commands.SetSpawn;
import com.ysl3000.commands.Spawn;
import com.ysl3000.commands.Storm;
import com.ysl3000.commands.Sun;
import com.ysl3000.commands.SurvivalGamemode;
import com.ysl3000.commands.SwitchLocation;
import com.ysl3000.commands.TimeDay;
import com.ysl3000.commands.TimeNight;
import com.ysl3000.commands.Walkspeed;
import com.ysl3000.events.BlockListener;
import com.ysl3000.events.ChestProtectionListener;
import com.ysl3000.events.EntityListener;
import com.ysl3000.events.MOTD;
import com.ysl3000.events.PlayerListener;
import com.ysl3000.events.SignListener;
import com.ysl3000.lib.CommandMaper;
import com.ysl3000.utils.ConfigLoader;
import com.ysl3000.utils.Metrics;
import com.ysl3000.utils.SmartController;
import com.ysl3000.utils.SpamFilter;

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
	public void onLoad() {
		Filter f = new SpamFilter();
		Bukkit.getLogger().setFilter(f);
		Logger.getLogger("Minecraft").setFilter(f);
	}

	@Override
	public void onEnable() {

		try {
			Metrics mc = new Metrics(this);
			mc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

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

		CommandMaper cmm = new CommandMaper(this);

		cmm.register(new AdminCommand());
		cmm.register(new BackCommand());
		cmm.register(new CheckCurrentGamemode());
		cmm.register(new CreativeGamemode());
		cmm.register(new SurvivalGamemode());
		cmm.register(new DoneCommand());
		cmm.register(new FlyMode());
		cmm.register(new FlySpeed());
		cmm.register(new TimeDay());
		cmm.register(new TimeNight());
		cmm.register(new Sun());
		cmm.register(new Storm());
		cmm.register(new GetWeather());
		cmm.register(new KillMe());
		cmm.register(new Kill());
		cmm.register(new Heal());
		cmm.register(new HealMe());
		cmm.register(new ServerInfo());
		cmm.register(new PlayerLookUpIp());
		cmm.register(new SwitchLocation());
		cmm.register(new SetSpawn());
		cmm.register(new Spawn());
		cmm.register(new Home());
		cmm.register(new Walkspeed());
		cmm.register(new God());
		cmm.register(new Online());
		cmm.register(new Seen());
		cmm.register(new Freeze());
		cmm.register(new RealTime());
		cmm.register(new ModCommand());
	}

	@Override
	public void onDisable() {

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

	public static ConfigLoader getConfigLoader() {
		return cfg;
	}

}
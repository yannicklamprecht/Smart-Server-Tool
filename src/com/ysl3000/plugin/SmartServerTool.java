package com.ysl3000.plugin;

import interfaces.ICommandMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.Factorizer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.ysl3000.commands.*;
import com.ysl3000.events.*;
import com.ysl3000.utils.*;

public class SmartServerTool extends JavaPlugin {

	protected FileConfiguration spawnConfig = null;
	protected File spawnConfigFile = null;


	public static final String mainDirectory = "plugins/SmartServerTool/";

	@Override
	public void onLoad() {
		Filter f = new SpamFilter();
		Bukkit.getLogger().setFilter(f);
		Logger.getLogger("Minecraft").setFilter(f);
		Factorizer.createPluginConfigLoader().registerHiddenPlugin(this);
	}

	@Override
	public void onEnable() {

		try {
			Metrics mc = new Metrics(this);
			mc.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ConfigFactorizer.createAndReturn(this);
		this.spawnConfig = this.getSpawnConfig();

		this.getSpawnConfig().options().copyDefaults(true);

		this.saveSpawnConfig();

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

		ICommandMapper cmm = Factorizer.createCommandMaper();

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
}
package com.ysl3000.plugin;


import com.ysl3000.commands.CommandRegistry;
import com.ysl3000.config.SmartSettings;
import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.events.EventRegistry;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SpamFilter;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.Logger;

public class SmartServerTool extends JavaPlugin {

    private CommandRegistry commandRegistry;
    private EventRegistry eventRegistry;
    private Utility utility = new Utility();
    private Prefix prefix = new Prefix();
    private SmartPlayers smartPlayers;

    private SmartSettings smartSettings;
    private WorldSpawnLocation worldSpawnLocation;

    private class SmartAdapterImpl implements EventRegistry.SmartAdapter {

        @Override
        public SmartSettings getSmartSettings() {
            return smartSettings;
        }

        @Override
        public SmartPlayers getSmartPlayers() {
            return smartPlayers;
        }

        @Override
        public Utility getUtility() {
            return utility;
        }

        @Override
        public Prefix getPrefix() {
            return prefix;
        }
    }


    @Override
    public void reloadConfig() {

        File settingsFile = new File(getDataFolder(), "settings.yml");
        File worldSpawnFile = new File(getDataFolder(), "worlds.yml");

        try {
            settingsFile.createNewFile();
            this.smartSettings = new Yaml()
                    .loadAs(new FileReader(settingsFile), SmartSettings.class);

            worldSpawnFile.createNewFile();
            this.worldSpawnLocation = new Yaml()
                    .loadAs(new FileReader(worldSpawnFile), WorldSpawnLocation.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        Filter f = new SpamFilter(spamConfigLoader);
        Bukkit.getLogger().setFilter(f);
        Logger.getLogger("Minecraft").setFilter(f);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        this.smartPlayers = new SmartPlayers();
        reloadConfig();
        this.commandRegistry = new CommandRegistry(smartPlayers);
        this.commandRegistry.registerCommands();
        this.eventRegistry = new EventRegistry(this, new SmartAdapterImpl());
        this.eventRegistry.register();

        Bukkit.getWorlds().forEach(world -> {
            Location spawnLocation = worldSpawnLocation.getSpawnpointForWorld(world);

            if (spawnLocation != null) {
                world.setSpawnLocation(spawnLocation);
            }
        });
    }
}
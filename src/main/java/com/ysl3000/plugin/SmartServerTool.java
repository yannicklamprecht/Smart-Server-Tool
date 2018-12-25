package com.ysl3000.plugin;


import com.ysl3000.commands.CommandRegistry;
import com.ysl3000.config.ConfigurationProvider;
import com.ysl3000.config.data.ConfigPosition;
import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.events.EventRegistry;
import com.ysl3000.utils.ISpamConfig;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SpamFilter;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

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
    private ISpamConfig iSpamConfig;
    private ConfigurationProvider configProvider;

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
        try {
            this.smartSettings = configProvider.getSmartSettings();
            this.worldSpawnLocation = configProvider.getWorldSpawnLocation();
            this.iSpamConfig = configProvider.getSpamConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoad() {
        Filter f = new SpamFilter(iSpamConfig);
        Bukkit.getLogger().setFilter(f);
        Logger.getLogger("Minecraft").setFilter(f);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onEnable() {
        this.smartPlayers = new SmartPlayers();
        this.configProvider = new ConfigurationProvider(getDataFolder());
        reloadConfig();
        this.commandRegistry = new CommandRegistry(smartPlayers);
        this.commandRegistry.registerCommands();
        this.eventRegistry = new EventRegistry(this, new SmartAdapterImpl());
        this.eventRegistry.register();

        Bukkit.getWorlds().forEach(world -> {
            ConfigPosition position = worldSpawnLocation.getSpawnpointForWorld(world.getName());
            world.setSpawnLocation(new Location(world,position.getX(),position.getY(),position.getZ()));
        });
    }
}
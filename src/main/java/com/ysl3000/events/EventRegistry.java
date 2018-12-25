package com.ysl3000.events;

import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.plugin.SmartPlayers;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.Utility;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class EventRegistry {

    private PluginManager pluginManager;
    private JavaPlugin javaPlugin;

    private SmartSettings smartSettings;
    private SmartPlayers smartPlayers;
    private Utility utility;
    private Prefix prefix;

    public EventRegistry(JavaPlugin javaPlugin, SmartAdapter smartAdapter) {
        this.pluginManager = javaPlugin.getServer().getPluginManager();
        this.javaPlugin = javaPlugin;
        this.smartSettings = smartAdapter.getSmartSettings();
        this.smartPlayers = smartAdapter.getSmartPlayers();
        this.utility = smartAdapter.getUtility();
        this.prefix = smartAdapter.getPrefix();
    }


    public void register() {
        registerEvents(
                new PlayerConnectionListener(smartPlayers),
                new ChestProtectionListener(),
                new BlockListener(smartSettings),
                new EntityListener(smartSettings.getWorldSettings(), smartSettings.getMisc()),
                new PlayerListener(smartPlayers, smartSettings),
                new SignListener(),
                new MOTD(utility, smartSettings.getMessages(), prefix, smartSettings.getMisc()),
                new PlayerStateListener(smartPlayers, smartSettings.getWorldSettings())
        );
    }


    private void registerEvents(Listener... listeners) {
        for (Listener listener : listeners) {
            pluginManager.registerEvents(listener, javaPlugin);
        }
    }


    public interface SmartAdapter {

        SmartSettings getSmartSettings();

        SmartPlayers getSmartPlayers();

        Utility getUtility();

        Prefix getPrefix();

    }


}

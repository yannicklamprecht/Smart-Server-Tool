package com.ysl3000.events;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Server;
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
  private Prefix prefix;
  private Server server;
  private MessageBuilder messageBuilder;
  private WorldSpawnWrapper worldSpawnWrapper;

  public EventRegistry(JavaPlugin javaPlugin, SmartAdapter smartAdapter) {
    this.pluginManager = javaPlugin.getServer().getPluginManager();
    this.javaPlugin = javaPlugin;
    this.smartSettings = smartAdapter.getSmartSettings();
    this.smartPlayers = smartAdapter.getSmartPlayers();
    this.prefix = smartAdapter.getPrefix();
    this.server = smartAdapter.getServer();
    this.messageBuilder = smartAdapter.getMessageBuilder();
    this.worldSpawnWrapper = smartAdapter.getWorldSpawnWrapper();
  }


  public void register() {
    registerEvents(
        new PlayerConnectionListener(smartPlayers),
        new ChestProtectionListener(),
        new BlockListener(smartSettings),
        new EntityListener(smartSettings.getWorldSettings(), smartSettings.getMisc()),
        new PlayerListener(smartPlayers, smartSettings),
        new SignListener(),
        new MOTD(smartSettings.getMessages(), prefix, smartSettings.getMisc(),
            server, messageBuilder),
        new PlayerStateListener(smartPlayers, smartSettings.getWorldSettings()),
        new WorldSpawnListener(worldSpawnWrapper)
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

    Prefix getPrefix();

    Server getServer();

    MessageBuilder getMessageBuilder();

    WorldSpawnWrapper getWorldSpawnWrapper();
  }


}

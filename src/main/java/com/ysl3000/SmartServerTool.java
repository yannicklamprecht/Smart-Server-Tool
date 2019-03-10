package com.ysl3000;


import com.ysl3000.commands.CommandRegistry;
import com.ysl3000.config.ConfigurationProvider;
import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.events.EventRegistry;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.RandColor;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

public class SmartServerTool extends JavaPlugin {

  private static final Logger LOGGER = Logger.getLogger(SmartServerTool.class.getName());

  private Prefix prefix;
  private SmartPlayers smartPlayers;

  private SmartSettings smartSettings;
  private ConfigurationProvider configProvider;
  private MessageBuilder messageBuilder;
  private WorldSpawnWrapper worldSpawnWrapper;

  @Override
  public void onDisable() {

    try {
      configProvider.saveSmartSettings(smartSettings);
    } catch (IOException e) {
      LOGGER.throwing(ConfigurationProvider.class.getName(), "saveSmartSettings", e);
    }
    try {
      configProvider.saveWorldSpawns(worldSpawnWrapper.getWorldSpawnLocation());
    } catch (IOException e) {
      LOGGER.throwing(ConfigurationProvider.class.getName(), "saveWorldSpawns", e);
    }

  }


  @Override
  public void onEnable() {
    this.prefix = new Prefix(new RandColor());

    this.smartSettings = new SmartSettings();

    Utility utility = new Utility(getServer());
    this.smartPlayers = new SmartPlayers();
    getDataFolder().mkdirs();
    this.configProvider = new ConfigurationProvider(getDataFolder(), getClassLoader());

    this.smartSettings = configProvider.loadSmartSettings();
    WorldSpawnLocation worldSpawnLocation = configProvider.getWorldSpawnLocation();
    this.worldSpawnWrapper = new WorldSpawnWrapper(worldSpawnLocation);

    this.messageBuilder = new MessageBuilder(getServer(), smartSettings.getMessages(), utility);
    CommandRegistry commandRegistry = new CommandRegistry(smartPlayers, utility, worldSpawnWrapper,
        smartSettings.getMessages().getCommandConfigContainer(),
        messageBuilder, this);
    commandRegistry.registerCommands();
    EventRegistry eventRegistry = new EventRegistry(this, new SmartAdapterImpl());
    eventRegistry.register();
  }

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
    public Prefix getPrefix() {
      return prefix;
    }

    @Override
    public Server getServer() {
      return SmartServerTool.this.getServer();
    }

    @Override
    public MessageBuilder getMessageBuilder() {
      return messageBuilder;
    }

    @Override
    public WorldSpawnWrapper getWorldSpawnWrapper() {
      return worldSpawnWrapper;
    }

  }

}
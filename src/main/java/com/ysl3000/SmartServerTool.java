package com.ysl3000;


import com.ysl3000.commands.CommandRegistry;
import com.ysl3000.config.ConfigurationProvider;
import com.ysl3000.config.adapter.ConfigPositionAdapter;
import com.ysl3000.config.adapter.LocationAdapter;
import com.ysl3000.config.data.IConfigPosition;
import com.ysl3000.config.data.WorldSpawnLocation;
import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.config.spam.ISpamConfig;
import com.ysl3000.events.EventRegistry;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SpamFilter;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class SmartServerTool extends JavaPlugin {

  private CommandRegistry commandRegistry;
  private EventRegistry eventRegistry;
  private Utility utility;
  private Prefix prefix = new Prefix();
  private SmartPlayers smartPlayers;

  private SmartSettings smartSettings;
  private WorldSpawnLocation worldSpawnLocation;
  private ISpamConfig iSpamConfig;
  private ConfigurationProvider configProvider;
  private MessageBuilder messageBuilder;

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
    Bukkit.getWorlds().forEach(this::setConfigFromWorldSpawnLocation);
  }

  @Override
  public void onEnable() {
    this.utility = new Utility(getServer());
    this.smartPlayers = new SmartPlayers();
    this.configProvider = new ConfigurationProvider(getDataFolder());
    this.reloadConfig();
    this.messageBuilder = new MessageBuilder(getServer(), smartSettings.getMessages(), utility);
    this.commandRegistry = new CommandRegistry(smartPlayers, utility);
    this.commandRegistry.registerCommands();
    this.eventRegistry = new EventRegistry(this, new SmartAdapterImpl());
    this.eventRegistry.register();
    Bukkit.getWorlds().forEach(this::setWorldSpawnLocationFromConfig);
  }

  private void setWorldSpawnLocationFromConfig(World world) {
    IConfigPosition position = worldSpawnLocation.getSpawnpointForWorld(world.getName());
    world.setSpawnLocation(new LocationAdapter(position));
  }

  private void setConfigFromWorldSpawnLocation(World world) {
    IConfigPosition position = worldSpawnLocation.getSpawnpointForWorld(world.getName());
    position.copyOf(new ConfigPositionAdapter(world.getSpawnLocation()));
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
  }
}
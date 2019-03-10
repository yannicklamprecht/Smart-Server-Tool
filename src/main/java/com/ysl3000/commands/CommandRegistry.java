package com.ysl3000.commands;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.messages.commands.CommandConfigContainer;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class CommandRegistry {

  private static final Logger LOGGER = Logger.getLogger(CommandRegistry.class.getName());

  private final WorldSpawnWrapper worldSpawnWrapper;
  private SimpleCommandMap commandMap;
  private SmartPlayers smartPlayers;
  private Utility utility;
  private CommandConfigContainer configContainer;
  private MessageBuilder messageBuilder;
  private JavaPlugin javaPlugin;

  public CommandRegistry(SmartPlayers smartPlayers, Utility utility,
      WorldSpawnWrapper worldSpawnWrapper,
      CommandConfigContainer commandConfigContainer,
      MessageBuilder messageBuilder,
      JavaPlugin javaPlugin) {
    this.smartPlayers = smartPlayers;
    this.utility = utility;
    this.worldSpawnWrapper = worldSpawnWrapper;
    this.configContainer = commandConfigContainer;
    this.messageBuilder = messageBuilder;
    this.javaPlugin = javaPlugin;

    try {
      Class<?> server = Bukkit.getServer().getClass();
      Field commandMapField = server.getDeclaredField("commandMap");
      commandMapField.setAccessible(true);
      this.commandMap = ((SimpleCommandMap) commandMapField.get(Bukkit.getServer()));
    } catch (Exception e) {
      LOGGER.throwing("CraftServer", "field:commandMap", e);
    }

  }

  public void registerCommands() {

    register(
        new AdminCommand(configContainer.getAdmin(), messageBuilder),
        new BackCommand(configContainer.getBack(), messageBuilder, smartPlayers),
        new CheckCurrentGamemode(configContainer.getCurrentGamemode(), messageBuilder),
        new GamemodeCommand(configContainer.getCreativeGamemode(), messageBuilder,
            GameMode.CREATIVE),
        new GamemodeCommand(configContainer.getSurvivalGamemode(), messageBuilder,
            GameMode.SURVIVAL),
        new DoneCommand(configContainer.getDone(), smartPlayers, messageBuilder),
        new FlyMode(configContainer.getFlyMode(), messageBuilder),
        new FlySpeed(configContainer.getFlySpeed(), messageBuilder),
        new GetWeather(configContainer.getGetWeather(), messageBuilder),
        new Freeze(configContainer.getFreeze(), smartPlayers, messageBuilder),
        new TimeCommand(configContainer.getTimeDay(),messageBuilder),
        new TimeCommand(configContainer.getTimeNight(),messageBuilder),
        new WeatherCommand(configContainer.getSun(),messageBuilder,false),
        new WeatherCommand(configContainer.getStorm(),messageBuilder,true),
        new Heal(configContainer.getHeal(), messageBuilder),
        new PlayerLookUpIp(configContainer.getIp(), messageBuilder),
        new SetSpawn(configContainer.getSetSpawn(), messageBuilder, worldSpawnWrapper),
        new Seen(configContainer.getSeen(), messageBuilder),
        new RealTime(configContainer.getRealTime(), messageBuilder),


        new KillMe(configContainer.getKillMe()),
        new Kill(configContainer.getKill(),messageBuilder),
        new ServerInfo(configContainer.getServerInfo(), messageBuilder),


        new SwitchLocation(configContainer.getSwitchLocations(),messageBuilder),
        new Spawn(configContainer.getSpawn(),messageBuilder),
        new Walkspeed(configContainer.getWalkspeed(),messageBuilder),



        new Home(configContainer.getHome()),
        new God(configContainer.getGod(),messageBuilder, smartPlayers),

        new Online(configContainer.getOnline(), messageBuilder),
        new ModCommand(configContainer.getMod(),messageBuilder, smartPlayers),
        new Configreload(configContainer.getReload(), javaPlugin)
    );

  }

  private void register(Command... commands) {
    commandMap.registerAll("sst", Arrays.asList(commands));
    Bukkit.getServer().getOnlinePlayers().forEach(Player::updateCommands);
  }
}

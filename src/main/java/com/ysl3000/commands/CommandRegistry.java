package com.ysl3000.commands;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.CommandConfigContainer;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
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
        new FlySpeed(configContainer.getFlySpeed()),
        new TimeDay(configContainer.getTimeDay()),
        new TimeNight(configContainer.getTimeNight()),
        new Sun(configContainer.getSun()),
        new Storm(configContainer.getStorm()),
        new GetWeather(configContainer.getGetWeather(), messageBuilder),
        new KillMe(configContainer.getKillMe()),
        new Kill(configContainer.getKill()),
        new Heal(configContainer.getHeal()),
        new HealMe(configContainer.getHealMe()),
        new ServerInfo(configContainer.getServerInfo()),
        new PlayerLookUpIp(configContainer.getIp()),
        new SwitchLocation(configContainer.getSwitchLocations()),
        new SetSpawn(configContainer.getSetSpawn(), worldSpawnWrapper),
        new Spawn(configContainer.getSpawn()),
        new Home(configContainer.getHome()),
        new Walkspeed(configContainer.getWalkspeed()),
        new God(configContainer.getGod(), smartPlayers),
        new Online(configContainer.getOnline(), utility),
        new Seen(configContainer.getSeen(), utility),
        new Freeze(configContainer.getFreeze(), smartPlayers, messageBuilder),
        new RealTime(configContainer.getRealTime(), utility),
        new ModCommand(configContainer.getMod(), smartPlayers),
        new Configreload(configContainer.getReload(), javaPlugin)
    );

  }

  private void register(Command... commands) {
    commandMap.registerAll("sst", Arrays.asList(commands));
  }
}

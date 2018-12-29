package com.ysl3000.commands;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.utils.Utility;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

/**
 * Created by ysl3000
 */
public class CommandRegistry {

  private static final Logger LOGGER = Logger.getLogger(CommandRegistry.class.getName());

  private final WorldSpawnWrapper worldSpawnWrapper;
  private SimpleCommandMap commandMap;
  private SmartPlayers smartPlayers;
  private Utility utility;

  public CommandRegistry(SmartPlayers smartPlayers, Utility utility,
      WorldSpawnWrapper worldSpawnWrapper) {
    this.smartPlayers = smartPlayers;
    this.utility = utility;
    this.worldSpawnWrapper = worldSpawnWrapper;

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
        new AdminCommand(),
        new BackCommand(smartPlayers),
        new CheckCurrentGamemode(),
        new CreativeGamemode(),
        new SurvivalGamemode(),
        new DoneCommand(smartPlayers),
        new FlyMode(),
        new FlySpeed(),
        new TimeDay(),
        new TimeNight(),
        new Sun(),
        new Storm(),
        new GetWeather(),
        new KillMe(),
        new Kill(),
        new Heal(),
        new HealMe(),
        new ServerInfo(),
        new PlayerLookUpIp(),
        new SwitchLocation(),
        new SetSpawn(worldSpawnWrapper),
        new Spawn(),
        new Home(),
        new Walkspeed(),
        new God(smartPlayers),
        new Online(utility),
        new Seen(utility),
        new Freeze(smartPlayers),
        new RealTime(utility),
        new ModCommand(smartPlayers)
    );

  }

  private void register(Command... commands) {
    commandMap.registerAll("sst", Arrays.asList(commands));
  }
}

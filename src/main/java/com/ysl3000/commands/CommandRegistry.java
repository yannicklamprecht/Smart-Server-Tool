package com.ysl3000.commands;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.CommandConfigContainer;
import com.ysl3000.config.settings.messages.PlayerMessage;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
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
  private CommandConfigContainer commandConfigContainer;
  private PlayerMessage playerMessage;
  private MessageBuilder messageBuilder;
  private JavaPlugin javaPlugin;

  public CommandRegistry(SmartPlayers smartPlayers, Utility utility,
      WorldSpawnWrapper worldSpawnWrapper,
      CommandConfigContainer commandConfigContainer,
      PlayerMessage playerMessage, MessageBuilder messageBuilder,
      JavaPlugin javaPlugin) {
    this.smartPlayers = smartPlayers;
    this.utility = utility;
    this.worldSpawnWrapper = worldSpawnWrapper;
    this.commandConfigContainer = commandConfigContainer;
    this.playerMessage = playerMessage;
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
        new AdminCommand(commandConfigContainer.getAdmin()),
        new BackCommand(commandConfigContainer.getBack(), smartPlayers),
        new CheckCurrentGamemode(commandConfigContainer.getCurrentGamemode()),
        new CreativeGamemode(commandConfigContainer.getCreativeGamemode()),
        new SurvivalGamemode(),
        new DoneCommand(commandConfigContainer.getDone(), smartPlayers),
        new FlyMode(commandConfigContainer.getFlyMode()),
        new FlySpeed(commandConfigContainer.getFlySpeed()),
        new TimeDay(commandConfigContainer.getTimeDay()),
        new TimeNight(),
        new Sun(),
        new Storm(),
        new GetWeather(commandConfigContainer.getGetWeather()),
        new KillMe(),
        new Kill(),
        new Heal(commandConfigContainer.getHeal()),
        new HealMe(commandConfigContainer.getHealMe()),
        new ServerInfo(),
        new PlayerLookUpIp(),
        new SwitchLocation(),
        new SetSpawn(worldSpawnWrapper),
        new Spawn(),
        new Home(commandConfigContainer.getHome()),
        new Walkspeed(commandConfigContainer.getWalkspeed()),
        new God(commandConfigContainer.getGod(), smartPlayers),
        new Online(utility),
        new Seen(utility),
        new Freeze(commandConfigContainer.getFreeze(), smartPlayers,
            playerMessage.getFreezeMessage(), messageBuilder),
        new RealTime(utility),
        new ModCommand(smartPlayers),
        new Configreload(commandConfigContainer.getReload(), javaPlugin)
    );

  }

  private void register(Command... commands) {
    commandMap.registerAll("sst", Arrays.asList(commands));
  }
}

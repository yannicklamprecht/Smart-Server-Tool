package com.ysl3000.config.settings;

import com.ysl3000.config.settings.messages.commands.AdminCommandMessage;
import com.ysl3000.config.settings.messages.commands.BackCommandMessage;
import com.ysl3000.config.settings.messages.commands.CheckCurrentGamemodeCommandMessage;
import com.ysl3000.config.settings.messages.commands.DoneCommandMessage;
import com.ysl3000.config.settings.messages.commands.FlyModeCommandMessage;
import com.ysl3000.config.settings.messages.commands.FlySpeedCommandMessage;
import com.ysl3000.config.settings.messages.commands.FreezeCommandMessage;
import com.ysl3000.config.settings.messages.commands.GamemodeCreativeCommandMessage;
import com.ysl3000.config.settings.messages.commands.GamemodeSurvivalCommandMessage;
import com.ysl3000.config.settings.messages.commands.GetWeatherCommandMessage;
import com.ysl3000.config.settings.messages.commands.PlayerLookUpIpCommandMessage;
import com.ysl3000.config.settings.messages.commands.RealTimeCommandMessage;
import com.ysl3000.config.settings.messages.commands.SeenCommandMessage;
import com.ysl3000.config.settings.messages.commands.TimeDayCommandMessage;
import com.ysl3000.config.settings.messages.commands.TimeNightCommandMessage;
import com.ysl3000.config.settings.messages.commands.WeatherStormCommandMessage;
import com.ysl3000.config.settings.messages.commands.WeatherSunCommandMessage;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class CommandConfigContainer {

  private FreezeCommandMessage freeze = new FreezeCommandMessage();

  private AdminCommandMessage admin = new AdminCommandMessage();

  private BackCommandMessage back = new BackCommandMessage();

  private CheckCurrentGamemodeCommandMessage currentGamemode = new CheckCurrentGamemodeCommandMessage();

  private GamemodeCreativeCommandMessage creativeGamemode = new GamemodeCreativeCommandMessage();

  private DoneCommandMessage done = new DoneCommandMessage();


  private FlyModeCommandMessage flyMode = new FlyModeCommandMessage();

  private FlySpeedCommandMessage flySpeed = new FlySpeedCommandMessage();


  private GetWeatherCommandMessage getWeather = new GetWeatherCommandMessage();

  private CommandConfig heal = new CommandConfig("heal", "heal someone", "/heal <player>",
      "sst.heal");

  private CommandConfig healMe = new CommandConfig("healme", "Heals you", "/healme",
      "sst.healme");

  private CommandConfig home = new CommandConfig("home", "teleport to home", "/home",
      "sst.home");

  private CommandConfig god = new CommandConfig("god", "Toggle godmode", "/god", "sst.god");


  private CommandConfig reload = new CommandConfig("smartreload", "Reloads SmartServerTool config",
      "/smartreload",
      "sst.reload");


  private CommandConfig walkspeed = new CommandConfig("ws", "set walkspeed",
      "/ws <amount/0.1-1.0>", "");

  private TimeDayCommandMessage timeDay = new TimeDayCommandMessage();

  private TimeNightCommandMessage timeNight = new TimeNightCommandMessage();

  private CommandConfig switchLocations = new CommandConfig("switch",
      "swap position with player",
      "/switch <player>", "sst.switch");

  private GamemodeSurvivalCommandMessage survivalGamemode = new GamemodeSurvivalCommandMessage();

  private WeatherSunCommandMessage sun = new WeatherSunCommandMessage();

  private WeatherStormCommandMessage storm = new WeatherStormCommandMessage();

  private CommandConfig spawn = new CommandConfig("spawn", "Teleport to Spawn", "/spawn",
      "sst.spawn");

  private CommandConfig setSpawn = new CommandConfig("setsp", "setspawn", "/setsp",
      "sst.setsp");

  private CommandConfig serverInfo = new CommandConfig("serverinfo", "get Serverinfo",
      "/serverinfo", "sst.info");

  private SeenCommandMessage seen = new SeenCommandMessage();

  private RealTimeCommandMessage realTime = new RealTimeCommandMessage();

  private PlayerLookUpIpCommandMessage ip = new PlayerLookUpIpCommandMessage();

  private CommandConfig online = new CommandConfig("online", "lists onlineplayer", "/online",
      "");

  private CommandConfig mod = new CommandConfig("mod", "enter modmode", "/mod", "sst.mod");


  private CommandConfig killMe = new CommandConfig("km", "kill's yourself", "/km",
      "sst.km");

  private CommandConfig kill = new CommandConfig("Kill", "kill player", "/kill <player>",
      "sst.kill");

}

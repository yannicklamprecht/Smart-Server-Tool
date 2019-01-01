package com.ysl3000.config.settings;

import com.ysl3000.config.settings.messages.commands.AdminCommandMessage;
import com.ysl3000.config.settings.messages.commands.BackCommandMessage;
import com.ysl3000.config.settings.messages.commands.CheckCurrentGamemodeCommandMessage;
import com.ysl3000.config.settings.messages.commands.DoneCommandMessage;
import com.ysl3000.config.settings.messages.commands.FlyModeCommandMessage;
import com.ysl3000.config.settings.messages.commands.FreezeCommandMessage;
import com.ysl3000.config.settings.messages.commands.GamemodeCreativeCommandMessage;
import com.ysl3000.config.settings.messages.commands.GamemodeSurvivalCommandMessage;
import com.ysl3000.config.settings.messages.commands.GetWeatherCommandMessage;
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

  private CommandConfig flySpeed = new CommandConfig("fs", "Set flyspeed", "/fs <amount>", "");


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

  private CommandConfig timeDay = new CommandConfig("td", "Set time to day", "/td", "sst.time");

  private CommandConfig timeNight = new CommandConfig("tn", "Set time to night", "/tn",
      "sst.time");

  private CommandConfig switchLocations = new CommandConfig("switch",
      "swap position with player",
      "/switch <player>", "sst.switch");

  private GamemodeSurvivalCommandMessage survivalGamemode = new GamemodeSurvivalCommandMessage();

  private CommandConfig sun = new CommandConfig("sun", "Set sun", "/sun", "sst.weather");

  private CommandConfig storm = new CommandConfig("storm", "Set storm", "/storm",
      "sst.weather");

  private CommandConfig spawn = new CommandConfig("spawn", "Teleport to Spawn", "/spawn",
      "sst.spawn");

  private CommandConfig setSpawn = new CommandConfig("setsp", "setspawn", "/setsp",
      "sst.setsp");

  private CommandConfig serverInfo = new CommandConfig("serverinfo", "get Serverinfo",
      "/serverinfo", "sst.info");

  private CommandConfig seen = new CommandConfig("seen", "check first/last seen",
      "/seen <player>", "sst.seen");


  private CommandConfig realTime = new CommandConfig("rt", "realtime", "/rt", "");

  private CommandConfig ip = new CommandConfig("/ip", "get ip of player",
      "//ip <player>", "sst.ip");

  private CommandConfig online = new CommandConfig("online", "lists onlineplayer", "/online",
      "");

  private CommandConfig mod = new CommandConfig("mod", "enter modmode", "/mod", "sst.mod");


  private CommandConfig killMe = new CommandConfig("km", "kill's yourself", "/km",
      "sst.km");

  private CommandConfig kill = new CommandConfig("Kill", "kill player", "/kill <player>",
      "sst.kill");

}

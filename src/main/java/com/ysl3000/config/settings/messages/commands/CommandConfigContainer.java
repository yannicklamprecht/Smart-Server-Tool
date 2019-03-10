package com.ysl3000.config.settings.messages.commands;

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
  private TimeDayCommandMessage timeDay = new TimeDayCommandMessage();
  private TimeNightCommandMessage timeNight = new TimeNightCommandMessage();
  private GamemodeSurvivalCommandMessage survivalGamemode = new GamemodeSurvivalCommandMessage();

  private WeatherSunCommandMessage sun = new WeatherSunCommandMessage();
  private WeatherStormCommandMessage storm = new WeatherStormCommandMessage();
  private SeenCommandMessage seen = new SeenCommandMessage();
  private RealTimeCommandMessage realTime = new RealTimeCommandMessage();

  private PlayerLookUpIpCommandMessage ip = new PlayerLookUpIpCommandMessage();
  private HealCommandMessage heal = new HealCommandMessage();


  private HomeCommandMessage home = new HomeCommandMessage();

  private GodCommandMessage god = new GodCommandMessage();


  private CommandConfig reload = new CommandConfig("smartreload", "Reloads SmartServerTool config",
      "/smartreload",
      "sst.reload");


  private WalkSpeedCommandMessage walkspeed = new WalkSpeedCommandMessage();


  private SwitchLocationCommandMessage switchLocations = new SwitchLocationCommandMessage();


  private SpawnCommandMessage spawn = new SpawnCommandMessage();

  private SetSpawnCommandMessage setSpawn = new SetSpawnCommandMessage();

  private ServerInfoCommandMessage serverInfo = new ServerInfoCommandMessage();


  private OnlineComandMessage online = new OnlineComandMessage();

  private ModCommandMessage mod = new ModCommandMessage();


  private CommandConfig killMe = new CommandConfig("km", "kill's yourself", "/km",
      "sst.km");

  private KillCommandMessage kill = new KillCommandMessage();

}

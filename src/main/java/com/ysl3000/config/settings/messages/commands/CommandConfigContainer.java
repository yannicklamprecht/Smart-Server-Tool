package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
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


  public FreezeCommandMessage getFreeze() {
    return freeze;
  }

  public void setFreeze(FreezeCommandMessage freeze) {
    this.freeze = freeze;
  }

  public AdminCommandMessage getAdmin() {
    return admin;
  }

  public void setAdmin(AdminCommandMessage admin) {
    this.admin = admin;
  }

  public BackCommandMessage getBack() {
    return back;
  }

  public void setBack(BackCommandMessage back) {
    this.back = back;
  }

  public CheckCurrentGamemodeCommandMessage getCurrentGamemode() {
    return currentGamemode;
  }

  public void setCurrentGamemode(
      CheckCurrentGamemodeCommandMessage currentGamemode) {
    this.currentGamemode = currentGamemode;
  }

  public GamemodeCreativeCommandMessage getCreativeGamemode() {
    return creativeGamemode;
  }

  public void setCreativeGamemode(
      GamemodeCreativeCommandMessage creativeGamemode) {
    this.creativeGamemode = creativeGamemode;
  }

  public DoneCommandMessage getDone() {
    return done;
  }

  public void setDone(DoneCommandMessage done) {
    this.done = done;
  }

  public FlyModeCommandMessage getFlyMode() {
    return flyMode;
  }

  public void setFlyMode(FlyModeCommandMessage flyMode) {
    this.flyMode = flyMode;
  }

  public FlySpeedCommandMessage getFlySpeed() {
    return flySpeed;
  }

  public void setFlySpeed(FlySpeedCommandMessage flySpeed) {
    this.flySpeed = flySpeed;
  }

  public GetWeatherCommandMessage getGetWeather() {
    return getWeather;
  }

  public void setGetWeather(
      GetWeatherCommandMessage getWeather) {
    this.getWeather = getWeather;
  }

  public TimeDayCommandMessage getTimeDay() {
    return timeDay;
  }

  public void setTimeDay(TimeDayCommandMessage timeDay) {
    this.timeDay = timeDay;
  }

  public TimeNightCommandMessage getTimeNight() {
    return timeNight;
  }

  public void setTimeNight(TimeNightCommandMessage timeNight) {
    this.timeNight = timeNight;
  }

  public GamemodeSurvivalCommandMessage getSurvivalGamemode() {
    return survivalGamemode;
  }

  public void setSurvivalGamemode(
      GamemodeSurvivalCommandMessage survivalGamemode) {
    this.survivalGamemode = survivalGamemode;
  }

  public WeatherSunCommandMessage getSun() {
    return sun;
  }

  public void setSun(WeatherSunCommandMessage sun) {
    this.sun = sun;
  }

  public WeatherStormCommandMessage getStorm() {
    return storm;
  }

  public void setStorm(WeatherStormCommandMessage storm) {
    this.storm = storm;
  }

  public SeenCommandMessage getSeen() {
    return seen;
  }

  public void setSeen(SeenCommandMessage seen) {
    this.seen = seen;
  }

  public RealTimeCommandMessage getRealTime() {
    return realTime;
  }

  public void setRealTime(RealTimeCommandMessage realTime) {
    this.realTime = realTime;
  }

  public PlayerLookUpIpCommandMessage getIp() {
    return ip;
  }

  public void setIp(PlayerLookUpIpCommandMessage ip) {
    this.ip = ip;
  }

  public HealCommandMessage getHeal() {
    return heal;
  }

  public void setHeal(HealCommandMessage heal) {
    this.heal = heal;
  }

  public HomeCommandMessage getHome() {
    return home;
  }

  public void setHome(HomeCommandMessage home) {
    this.home = home;
  }

  public GodCommandMessage getGod() {
    return god;
  }

  public void setGod(GodCommandMessage god) {
    this.god = god;
  }

  public CommandConfig getReload() {
    return reload;
  }

  public void setReload(CommandConfig reload) {
    this.reload = reload;
  }

  public WalkSpeedCommandMessage getWalkspeed() {
    return walkspeed;
  }

  public void setWalkspeed(WalkSpeedCommandMessage walkspeed) {
    this.walkspeed = walkspeed;
  }

  public SwitchLocationCommandMessage getSwitchLocations() {
    return switchLocations;
  }

  public void setSwitchLocations(
      SwitchLocationCommandMessage switchLocations) {
    this.switchLocations = switchLocations;
  }

  public SpawnCommandMessage getSpawn() {
    return spawn;
  }

  public void setSpawn(SpawnCommandMessage spawn) {
    this.spawn = spawn;
  }

  public SetSpawnCommandMessage getSetSpawn() {
    return setSpawn;
  }

  public void setSetSpawn(SetSpawnCommandMessage setSpawn) {
    this.setSpawn = setSpawn;
  }

  public ServerInfoCommandMessage getServerInfo() {
    return serverInfo;
  }

  public void setServerInfo(
      ServerInfoCommandMessage serverInfo) {
    this.serverInfo = serverInfo;
  }

  public OnlineComandMessage getOnline() {
    return online;
  }

  public void setOnline(OnlineComandMessage online) {
    this.online = online;
  }

  public ModCommandMessage getMod() {
    return mod;
  }

  public void setMod(ModCommandMessage mod) {
    this.mod = mod;
  }

  public CommandConfig getKillMe() {
    return killMe;
  }

  public void setKillMe(CommandConfig killMe) {
    this.killMe = killMe;
  }

  public KillCommandMessage getKill() {
    return kill;
  }

  public void setKill(KillCommandMessage kill) {
    this.kill = kill;
  }
}

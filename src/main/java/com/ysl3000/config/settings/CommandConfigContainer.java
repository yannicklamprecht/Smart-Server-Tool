package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class CommandConfigContainer {

  private CommandConfig freeze = new CommandConfig("freeze", "freezes a player",
      "/freeze <player>", "sst.freeze");

  private CommandConfig admin = new CommandConfig("/admin", "Toggle op", "//admin", "sst.admin");

  private CommandConfig back = new CommandConfig("back", "tp to last location", "/back", "");

  private CommandConfig currentGamemode = new CommandConfig("gm", "Current GameMode", "/gm",
      "sst.gm");

  private CommandConfig creativeGamemode = new CommandConfig("gmc", "Set Creative", "/gmc",
      "sst.gamemode");

  private CommandConfig done = new CommandConfig("done", "Leave modmode", "/done", "sst.mod");


  private CommandConfig flyMode = new CommandConfig("fly", "toggle fly", "/fly <player>",
      "sst.fly");

  private CommandConfig flySpeed = new CommandConfig("fs", "Set flyspeed", "/fs <amount>", "");


  private CommandConfig getWeather = new CommandConfig("wg", "get weather", "/wg", "sst.wg");

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

  public CommandConfig getFreeze() {
    return freeze;
  }

  public void setFreeze(CommandConfig freeze) {
    this.freeze = freeze;
  }

  public CommandConfig getAdmin() {
    return admin;
  }

  public void setAdmin(CommandConfig admin) {
    this.admin = admin;
  }

  public CommandConfig getBack() {
    return back;
  }

  public void setBack(CommandConfig back) {
    this.back = back;
  }

  public CommandConfig getCurrentGamemode() {
    return currentGamemode;
  }

  public void setCurrentGamemode(CommandConfig currentGamemode) {
    this.currentGamemode = currentGamemode;
  }

  public CommandConfig getCreativeGamemode() {
    return creativeGamemode;
  }

  public void setCreativeGamemode(CommandConfig creativeGamemode) {
    this.creativeGamemode = creativeGamemode;
  }

  public CommandConfig getDone() {
    return done;
  }

  public void setDone(CommandConfig done) {
    this.done = done;
  }

  public CommandConfig getFlyMode() {
    return flyMode;
  }

  public void setFlyMode(CommandConfig flyMode) {
    this.flyMode = flyMode;
  }

  public CommandConfig getFlySpeed() {
    return flySpeed;
  }

  public CommandConfig getGetWeather() {
    return getWeather;
  }

  public void setGetWeather(CommandConfig getWeather) {
    this.getWeather = getWeather;
  }

  public CommandConfig getHeal() {
    return heal;
  }

  public void setHeal(CommandConfig heal) {
    this.heal = heal;
  }

  public CommandConfig getHealMe() {
    return healMe;
  }

  public void setHealMe(CommandConfig healMe) {
    this.healMe = healMe;
  }

  public CommandConfig getHome() {
    return home;
  }

  public void setHome(CommandConfig home) {
    this.home = home;
  }

  public CommandConfig getGod() {
    return god;
  }

  public void setGod(CommandConfig god) {
    this.god = god;
  }

  public CommandConfig getReload() {
    return reload;
  }

  public void setReload(CommandConfig reload) {
    this.reload = reload;
  }

  public CommandConfig getWalkspeed() {
    return walkspeed;
  }

  public void setWalkspeed(CommandConfig walkspeed) {
    this.walkspeed = walkspeed;
  }

  public CommandConfig getTimeDay() {
    return timeDay;
  }

  public void setTimeDay(CommandConfig timeDay) {
    this.timeDay = timeDay;
  }
}

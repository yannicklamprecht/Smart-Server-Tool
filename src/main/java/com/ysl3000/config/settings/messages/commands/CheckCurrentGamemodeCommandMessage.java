package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class CheckCurrentGamemodeCommandMessage extends CommandConfig {

  private String currentGamemode = "Current GameMode &6{player_gamemode}";
  private String playerNotFound = "Player {parameter} not found!";
  private String currentGamemodeOf = "Current GameMode of {player_display_name} {player_gamemode}";
  private String youAreNotAllowedToLookupOthersGamemode = "You aren't allowed to lookup others gamemode";

  public CheckCurrentGamemodeCommandMessage() {

    super("gm", "Current GameMode", "/gm",
        "sst.gm");

  }

  public String getCurrentGamemode() {
    return currentGamemode;
  }

  public void setCurrentGamemode(String currentGamemode) {
    this.currentGamemode = currentGamemode;
  }

  public String getPlayerNotFound() {
    return playerNotFound;
  }

  public void setPlayerNotFound(String playerNotFound) {
    this.playerNotFound = playerNotFound;
  }

  public String getCurrentGamemodeOf() {
    return currentGamemodeOf;
  }

  public void setCurrentGamemodeOf(String currentGamemodeOf) {
    this.currentGamemodeOf = currentGamemodeOf;
  }

  public String getYouAreNotAllowedToLookupOthersGamemode() {
    return youAreNotAllowedToLookupOthersGamemode;
  }

  public void setYouAreNotAllowedToLookupOthersGamemode(
      String youAreNotAllowedToLookupOthersGamemode) {
    this.youAreNotAllowedToLookupOthersGamemode = youAreNotAllowedToLookupOthersGamemode;
  }
}

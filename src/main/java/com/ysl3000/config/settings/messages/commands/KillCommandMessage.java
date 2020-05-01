package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class KillCommandMessage extends CommandConfig {

  private String notEnoughArguments = "Need more arguments";
  private String playerNotFound = "Player not found";
  private String playerSuccessfullyKilled = "{player_display_name} killed successfully";

  KillCommandMessage() {
    super("Kill", "kill player", "/kill <player>",
        "sst.kill");
  }

  public String getNotEnoughArguments() {
    return notEnoughArguments;
  }

  public void setNotEnoughArguments(String notEnoughArguments) {
    this.notEnoughArguments = notEnoughArguments;
  }

  public String getPlayerNotFound() {
    return playerNotFound;
  }

  public void setPlayerNotFound(String playerNotFound) {
    this.playerNotFound = playerNotFound;
  }

  public String getPlayerSuccessfullyKilled() {
    return playerSuccessfullyKilled;
  }

  public void setPlayerSuccessfullyKilled(String playerSuccessfullyKilled) {
    this.playerSuccessfullyKilled = playerSuccessfullyKilled;
  }
}

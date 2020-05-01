package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class PlayerLookUpIpCommandMessage extends CommandConfig {

  private String playerNotFound = "PLAYER {parameter} isn't found";
  private String displayIpAddressOfPlayerMessage = "Ip of &6 {player_name} &fis&e{player_address}";

  public PlayerLookUpIpCommandMessage() {
    super("/ip", "get ip of player",
        "//ip <player>", "sst.ip");
  }

  public String getPlayerNotFound() {
    return playerNotFound;
  }

  public void setPlayerNotFound(String playerNotFound) {
    this.playerNotFound = playerNotFound;
  }

  public String getDisplayIpAddressOfPlayerMessage() {
    return displayIpAddressOfPlayerMessage;
  }

  public void setDisplayIpAddressOfPlayerMessage(String displayIpAddressOfPlayerMessage) {
    this.displayIpAddressOfPlayerMessage = displayIpAddressOfPlayerMessage;
  }
}

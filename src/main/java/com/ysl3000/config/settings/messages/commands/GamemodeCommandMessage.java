package com.ysl3000.config.settings.messages.commands;

import org.bukkit.ChatColor;

/**
 * Created by ysl3000
 */
public abstract class GamemodeCommandMessage extends CommandConfig {

  private String enterGamemodeMode = "Enter &6{player_gamemode}";
  private String playerNotFound = "PLAYER {parameter} isn't found";
  private String gamemodeTarget = "&aYou Gamemode now is {gamemode}, set by &5{player_name}";
  private String gamemodeSender =
      ChatColor.GREEN + "&aYou set {player_gamemode} for &5{player_name}";


  public GamemodeCommandMessage(String name, String description, String usageMessage,
      String permission) {
    super(name, description, usageMessage, permission);
  }

  public String getEnterGamemodeMode() {
    return enterGamemodeMode;
  }

  public void setEnterGamemodeMode(String enterGamemodeMode) {
    this.enterGamemodeMode = enterGamemodeMode;
  }

  public String getPlayerNotFound() {
    return playerNotFound;
  }

  public void setPlayerNotFound(String playerNotFound) {
    this.playerNotFound = playerNotFound;
  }

  public String getGamemodeTarget() {
    return gamemodeTarget;
  }

  public void setGamemodeTarget(String gamemodeTarget) {
    this.gamemodeTarget = gamemodeTarget;
  }

  public String getGamemodeSender() {
    return gamemodeSender;
  }

  public void setGamemodeSender(String gamemodeSender) {
    this.gamemodeSender = gamemodeSender;
  }
}

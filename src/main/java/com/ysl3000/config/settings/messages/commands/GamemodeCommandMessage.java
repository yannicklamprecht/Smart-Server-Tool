package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;
import org.bukkit.ChatColor;

/**
 * Created by ysl3000
 */
@Data
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
}

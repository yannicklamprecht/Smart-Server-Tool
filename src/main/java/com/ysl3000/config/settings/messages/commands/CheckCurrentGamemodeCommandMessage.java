package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class CheckCurrentGamemodeCommandMessage extends CommandConfig {

  private String currentGamemode = "Current GameMode &6{player_gamemode}";
  private String playerNotFound = "Player {parameter} not found!";
  private String currentGamemodeOf = "Current GameMode of {player_display_name} {player_gamemode}";
  private String youAreNotAllowedToLookupOthersGamemode = "You aren't allowed to lookup others gamemode";
  public CheckCurrentGamemodeCommandMessage() {

    super("gm", "Current GameMode", "/gm",
        "sst.gm");
  }
}

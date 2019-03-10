package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class KillCommandMessage extends CommandConfig {

  private String notEnoughArguments = "Need more arguments";
  private String playerNotFound = "Player not found";
  private String playerSuccessfullyKilled = "{player_display_name} killed successfully";

  KillCommandMessage(){
  super("Kill", "kill player", "/kill <player>",
      "sst.kill");
  }

}

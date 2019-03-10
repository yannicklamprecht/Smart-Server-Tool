package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class SwitchLocationCommandMessage extends CommandConfig {


  private String notEnoughArguments = "Not enough arguments";

  private String tooManyArguments = "Too many arguments";

  private String notOnline = "Not online";

  private String switchMessage = "You changed position with {player_display_name}"; // target

  private String additionalMessageSwitch ="{player_display_name} changed position with you. Changed by {player_display_name}";

  SwitchLocationCommandMessage(){
    super("switch",
        "swap position with player",
        "/switch <player>", "sst.switch");
  }
}

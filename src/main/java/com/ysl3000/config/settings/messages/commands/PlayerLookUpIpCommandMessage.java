package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class PlayerLookUpIpCommandMessage extends CommandConfig {

  public PlayerLookUpIpCommandMessage(){
    super("/ip", "get ip of player",
        "//ip <player>", "sst.ip");
  }

  private String playerNotFound = "PLAYER {parameter} isn't found";
  private String displayIpAddressOfPlayerMessage = "Ip of &6 {player_name} &fis&e{player_address}";

}

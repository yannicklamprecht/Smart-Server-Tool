package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class SetSpawnCommandMessage extends CommandConfig {


  private String setSpawnMessage = "Spawn of {world_name} set";

  public SetSpawnCommandMessage() {
    super("setsp", "setspawn", "/setsp",
        "sst.setsp");
  }

}

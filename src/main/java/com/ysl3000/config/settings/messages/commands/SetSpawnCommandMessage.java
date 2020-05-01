package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class SetSpawnCommandMessage extends CommandConfig {


  private String setSpawnMessage = "Spawn of {world_name} set";

  public SetSpawnCommandMessage() {
    super("setsp", "setspawn", "/setsp",
        "sst.setsp");
  }

  public String getSetSpawnMessage() {
    return setSpawnMessage;
  }

  public void setSetSpawnMessage(String setSpawnMessage) {
    this.setSpawnMessage = setSpawnMessage;
  }
}

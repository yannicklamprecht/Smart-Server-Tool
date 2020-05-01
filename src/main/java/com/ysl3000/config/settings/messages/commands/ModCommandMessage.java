package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class ModCommandMessage extends CommandConfig {

  private String modModeActive = "&aModmode enabled";


  public ModCommandMessage() {
    super("mod", "enter modmode", "/mod", "sst.mod");
  }

  public String getModModeActive() {
    return modModeActive;
  }

  public void setModModeActive(String modModeActive) {
    this.modModeActive = modModeActive;
  }
}

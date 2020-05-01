package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class HomeCommandMessage extends CommandConfig {

  private String homeNotSet = "No home set";

  public HomeCommandMessage() {
    super("home", "teleport to home", "/home",
        "sst.home");
  }

  public String getHomeNotSet() {
    return homeNotSet;
  }

  public void setHomeNotSet(String homeNotSet) {
    this.homeNotSet = homeNotSet;
  }
}

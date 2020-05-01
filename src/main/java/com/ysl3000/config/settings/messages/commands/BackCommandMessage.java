package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class BackCommandMessage extends CommandConfig {

  private String lastLocationNotFound = "Last location not found";

  public BackCommandMessage() {
    super("back", "tp to last location", "/back", "");
  }

  public String getLastLocationNotFound() {
    return lastLocationNotFound;
  }

  public void setLastLocationNotFound(String lastLocationNotFound) {
    this.lastLocationNotFound = lastLocationNotFound;
  }
}

package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class RealTimeCommandMessage extends CommandConfig {

  private String currentTime = "Current Time &6{server_time}";

  public RealTimeCommandMessage() {
    super("rt", "realtime", "/rt", "");
  }

  public String getCurrentTime() {
    return currentTime;
  }

  public void setCurrentTime(String currentTime) {
    this.currentTime = currentTime;
  }
}

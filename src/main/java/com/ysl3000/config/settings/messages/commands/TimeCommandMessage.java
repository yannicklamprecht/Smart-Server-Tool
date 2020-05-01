package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public abstract class TimeCommandMessage extends CommandConfig {

  private String timeSet = "Time set to &6 {parameter}";
  private int time;
  private String type;


  public TimeCommandMessage(String name, String description, String usageMessage,
      String permission, int time, String type) {
    super(name, description, usageMessage, permission);
    this.time = time;
    this.type = type;
  }

  public String getTimeSet() {
    return timeSet;
  }

  public void setTimeSet(String timeSet) {
    this.timeSet = timeSet;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

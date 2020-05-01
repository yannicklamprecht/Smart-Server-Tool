package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class WalkSpeedCommandMessage extends CommandConfig {

  private String walkSpeedSetTo = "Walkspeed set to {walk_speed}";
  private String walkSpeedNeedToBeBetween = "Speed has to be between 0.1 and 1.0";

  WalkSpeedCommandMessage() {
    super("ws", "set walkspeed",
        "/ws <amount/0.1-1.0>", "");
  }

  public String getWalkSpeedSetTo() {
    return walkSpeedSetTo;
  }

  public void setWalkSpeedSetTo(String walkSpeedSetTo) {
    this.walkSpeedSetTo = walkSpeedSetTo;
  }

  public String getWalkSpeedNeedToBeBetween() {
    return walkSpeedNeedToBeBetween;
  }

  public void setWalkSpeedNeedToBeBetween(String walkSpeedNeedToBeBetween) {
    this.walkSpeedNeedToBeBetween = walkSpeedNeedToBeBetween;
  }
}

package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class FlySpeedCommandMessage extends CommandConfig {


  private String flySpeedSetTo = "Flyspeed set to {fly_speed}";
  private String flySpeedNeedToBeBetween = "Speed has to be between 0.1 and 1.0";

  public FlySpeedCommandMessage() {
    super("fs", "Set flyspeed", "/fs <amount>", "");
  }

  public String getFlySpeedSetTo() {
    return flySpeedSetTo;
  }

  public void setFlySpeedSetTo(String flySpeedSetTo) {
    this.flySpeedSetTo = flySpeedSetTo;
  }

  public String getFlySpeedNeedToBeBetween() {
    return flySpeedNeedToBeBetween;
  }

  public void setFlySpeedNeedToBeBetween(String flySpeedNeedToBeBetween) {
    this.flySpeedNeedToBeBetween = flySpeedNeedToBeBetween;
  }
}

package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class FlySpeedCommandMessage extends CommandConfig {


  private String flySpeedSetTo = "Flyspeed set to {fly_speed}";
  private String flySpeedNeedToBeBetween = "Speed has to be between 0.1 and 1.0";

  public FlySpeedCommandMessage(){
    super("fs", "Set flyspeed", "/fs <amount>", "");
  }
}

package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class HomeCommandMessage extends CommandConfig {

  private String homeNotSet = "No home set";

  public HomeCommandMessage(){
    super("home", "teleport to home", "/home",
        "sst.home");
  }

}

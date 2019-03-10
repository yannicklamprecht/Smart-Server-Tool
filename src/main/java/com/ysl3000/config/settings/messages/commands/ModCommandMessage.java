package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class ModCommandMessage extends CommandConfig {

  private String modModeActive = "&aModmode enabled";


  public ModCommandMessage(){
    super("mod", "enter modmode", "/mod", "sst.mod");
  }

}

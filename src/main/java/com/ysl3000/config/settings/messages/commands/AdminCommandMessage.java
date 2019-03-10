package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class AdminCommandMessage extends CommandConfig {

  private String operatorDisabled = "&4Op disabled";
  private String operatorEnabled = "&aOp enabled";
  private String pleaseUseDeopPlayer = "Please use /deop <player>";

  public AdminCommandMessage(){
    super("/admin", "Toggle op", "//admin", "sst.admin");
  }

}

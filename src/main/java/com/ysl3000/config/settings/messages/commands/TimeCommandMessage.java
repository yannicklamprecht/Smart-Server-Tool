package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public abstract class TimeCommandMessage extends CommandConfig {

  private String timeSet = "Time set to &6 {parameter}";
  private int time;
  private String type;


  public TimeCommandMessage(String name, String description, String usageMessage,
      String permission,int time, String type){
    super(name,description,usageMessage,permission);
    this.time = time;
    this.type = type;
  }
}

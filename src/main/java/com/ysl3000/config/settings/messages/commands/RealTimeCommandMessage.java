package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class RealTimeCommandMessage extends CommandConfig {

  private String currentTime = "Current Time &6{server_time}";

  public RealTimeCommandMessage() {
    super("rt", "realtime", "/rt", "");
  }

}

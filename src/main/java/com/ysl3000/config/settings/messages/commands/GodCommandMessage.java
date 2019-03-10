package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class GodCommandMessage extends CommandConfig {

  private String godmodeMessage = "Godmode is now {god{activated:deactivated}}";

  public GodCommandMessage() {
    super("god", "toggle godmode", "/god",
        "sst.god");
  }

}

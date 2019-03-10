package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class BackCommandMessage extends CommandConfig {

  private String lastLocationNotFound = "Last location not found";

  public BackCommandMessage() {
    super("back", "tp to last location", "/back", "");
  }
}

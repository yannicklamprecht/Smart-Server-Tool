package com.ysl3000.config.settings.messages.commands;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ysl3000
 */
@Setter
@Getter
public class DoneCommandMessage extends CommandConfig {

  private String modmodeDisabled = "&4modmode disabled";
  private String doneTarget = "&4modmode disabled by {player_name}";
  private String doneSender = "&4modmode disabled for {player_name}";

  public DoneCommandMessage() {
    super("done", "Leave modmode", "/done", "sst.mod");
  }

}

package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class TimeDayCommandMessage extends TimeCommandMessage {

  public TimeDayCommandMessage() {
    super("td", "Set time to day", "/td", "sst.time", 0, "Day");
  }

}

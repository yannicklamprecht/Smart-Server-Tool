package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class TimeDayCommandMessage extends TimeCommandMessage {

  public TimeDayCommandMessage() {
    super("td", "Set time to day", "/td", "sst.time", 0, "Day");
  }

}

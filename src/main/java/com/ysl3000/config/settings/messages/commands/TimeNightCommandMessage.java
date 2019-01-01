package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class TimeNightCommandMessage extends TimeCommandMessage {

  public TimeNightCommandMessage() {
    super("tn", "Set time to night", "/tn",
        "sst.time",18_000,"Night");
  }
}

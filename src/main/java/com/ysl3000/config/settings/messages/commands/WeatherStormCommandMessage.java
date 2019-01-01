package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class WeatherStormCommandMessage extends WeatherCommandMessage {

  public WeatherStormCommandMessage() {
    super("storm", "Set storm", "/storm",
        "sst.weather", "Weather set to &6Storm");
  }
}

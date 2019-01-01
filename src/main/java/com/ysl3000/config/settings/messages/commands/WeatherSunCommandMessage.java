package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class WeatherSunCommandMessage extends WeatherCommandMessage {
  public WeatherSunCommandMessage() {
    super("sun", "Set sun", "/sun", "sst.weather", "Weather set to &6Sun");
  }
}

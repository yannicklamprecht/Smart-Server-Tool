package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class WeatherCommandMessage extends CommandConfig {


  private String weatherMessage;

  public WeatherCommandMessage(String name, String description, String usageMessage,
      String permission, String weatherMessage) {
    super(name, description, usageMessage, permission);
    this.weatherMessage = weatherMessage;
  }

  public String getWeatherMessage() {
    return weatherMessage;
  }

  public void setWeatherMessage(String weatherMessage) {
    this.weatherMessage = weatherMessage;
  }
}

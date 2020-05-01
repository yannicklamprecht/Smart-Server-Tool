package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class GetWeatherCommandMessage extends CommandConfig {

  private String currentWeather = "Current Weather in {world_name} is &6{weather{rainy:sunny}}";

  public GetWeatherCommandMessage() {
    super("wg", "get weather", "/wg", "sst.wg");
  }

  public String getCurrentWeather() {
    return currentWeather;
  }

  public void setCurrentWeather(String currentWeather) {
    this.currentWeather = currentWeather;
  }
}

package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class GetWeatherCommandMessage extends CommandConfig {

  private String currentWeather = "Current Weather in {world_name} is &6{weather{rainy:sunny}}";

  public GetWeatherCommandMessage(){
    super("wg", "get weather", "/wg", "sst.wg");
  }


}

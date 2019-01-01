package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class WeatherCommandMessage extends CommandConfig {


  private String weatherMessage;

  public WeatherCommandMessage(String name, String description, String usageMessage,
      String permission, String weatherMessage) {
    super(name, description, usageMessage, permission);
    this.weatherMessage = weatherMessage;
  }

}

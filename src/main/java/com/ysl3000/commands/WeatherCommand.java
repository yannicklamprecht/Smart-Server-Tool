package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.WeatherCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommand extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private final boolean rain;
  private final WeatherCommandMessage weatherCommandMessage;

  public WeatherCommand(WeatherCommandMessage commandConfig, MessageBuilder messageBuilder,
      boolean rain) {
    super(commandConfig);
    this.messageBuilder = messageBuilder;
    this.rain = rain;
    this.weatherCommandMessage = commandConfig;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;
    if (testPermission(p)) {
      p.getWorld().setStorm(rain);
      p.getWorld().setThundering(rain);
      p.sendMessage(messageBuilder.injectParameter(weatherCommandMessage.getWeatherMessage()));
    }
    return true;
  }
}

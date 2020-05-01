package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.GetWeatherCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GetWeather extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private final GetWeatherCommandMessage getWeatherCommandMessage;

  public GetWeather(GetWeatherCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.getWeatherCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;
    if (testPermission(player)) {
      player.sendMessage(messageBuilder
          .injectParameter(getWeatherCommandMessage.getCurrentWeather(), player.getWorld()));
    }
    return true;
  }
}

package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.RealTimeCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;

/**
 * @author yannicklamprecht
 */
public class RealTime extends CustomCommand {


  private MessageBuilder messageBuilder;
  private RealTimeCommandMessage realTimeCommandMessage;

  RealTime(RealTimeCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.messageBuilder = messageBuilder;
    this.realTimeCommandMessage = commandConfig;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    sender.sendMessage(messageBuilder.injectParameter(realTimeCommandMessage.getCurrentTime()));
    return true;
  }
}

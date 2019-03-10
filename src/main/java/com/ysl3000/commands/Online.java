package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.OnlineComandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Online extends CustomCommand {


  private final OnlineComandMessage commandConfig;
  private final MessageBuilder messageBuilder;

  Online(OnlineComandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.commandConfig = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }
    sender.sendMessage(messageBuilder.injectParameter(commandConfig.getOnlineMessage()));

    return true;
  }
}

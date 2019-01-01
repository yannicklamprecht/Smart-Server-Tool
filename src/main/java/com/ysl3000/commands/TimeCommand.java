package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.TimeCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class TimeCommand extends CustomCommand {

  private final TimeCommandMessage commandConfig;
  private final MessageBuilder messageBuilder;

  public TimeCommand(TimeCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.commandConfig = commandConfig;
    this.messageBuilder = messageBuilder;
  }


  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    displayNotifyMessage(player, commandConfig.getTime(), commandConfig.getType());
    return true;
  }

  private void displayNotifyMessage(Player player, long time, String type) {
    if (testPermission(player)) {
      player.getWorld().setTime(time);
      player.sendMessage(messageBuilder.injectParameter(commandConfig.getTimeSet(), type));
    }
  }
}

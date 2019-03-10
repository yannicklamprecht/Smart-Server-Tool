package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.WalkSpeedCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.regex.Pattern;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Walkspeed extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");
  private final WalkSpeedCommandMessage commandConfig;
  private final MessageBuilder messageBuilder;


  Walkspeed(WalkSpeedCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.commandConfig = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (args.length != 1) {
      sender.sendMessage(this.getUsage());
      return false;
    }
    Player p = (Player) sender;

    if (NUMBER.matcher(args[0]).matches()) {
      if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
        p.setWalkSpeed(Float.parseFloat(args[0]));
        p.sendMessage(messageBuilder.injectParameter(commandConfig.getWalkSpeedSetTo(), p));
      } else {
        p.sendMessage(messageBuilder.injectParameter(commandConfig.getWalkSpeedNeedToBeBetween()));
      }
    }

    return true;
  }
}

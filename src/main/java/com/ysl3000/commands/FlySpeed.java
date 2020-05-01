package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.FlySpeedCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.regex.Pattern;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class FlySpeed extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");

  private final FlySpeedCommandMessage flySpeedCommandMessage;
  private final MessageBuilder messageBuilder;

  public FlySpeed(FlySpeedCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.flySpeedCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    if (args.length != 1) {
      sender.sendMessage(this.getUsage());
      return false;
    }
    Player p = (Player) sender;

    if (!NUMBER.matcher(args[0]).matches()) {
      return false;
    }
    float speed = Float.parseFloat(args[0]);

    if (speed > 0 && speed <= 1) {
      p.setFlySpeed(speed);

      p.sendMessage(messageBuilder.injectParameter(flySpeedCommandMessage.getFlySpeedSetTo(), p));
    } else {
      p.sendMessage(
          messageBuilder.injectParameter(flySpeedCommandMessage.getFlySpeedNeedToBeBetween()));
    }
    return true;
  }
}

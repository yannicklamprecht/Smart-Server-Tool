package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.SwitchLocationCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class SwitchLocation extends CustomCommand {


  private final SwitchLocationCommandMessage switchLocationCommandMessage;
  private final MessageBuilder messageBuilder;

  SwitchLocation(SwitchLocationCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.switchLocationCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {
      if (args.length == 0) {
        player.sendMessage(switchLocationCommandMessage.getNotEnoughArguments());
      } else if (args.length == 1) {
        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
          player.sendMessage(switchLocationCommandMessage.getNotOnline());
          return true;
        }

        Location loca = player.getLocation();
        player.teleport(target.getLocation());
        target.teleport(loca);

        if (target.canSee(player)) {

          player.sendMessage(messageBuilder
              .injectParameter(switchLocationCommandMessage.getSwitchMessage(), target));
          target.sendMessage(messageBuilder
              .injectParameter(switchLocationCommandMessage.getAdditionalMessageSwitch(), player));
        }
      } else {
        player.sendMessage(
            messageBuilder.injectParameter(switchLocationCommandMessage.getTooManyArguments()));
      }
    }
    return true;
  }
}

package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.FlyModeCommandMessage;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyMode extends CustomCommand {


  private final MessageBuilder messageBuilder;
  private final FlyModeCommandMessage flyModeCommandMessage;

  public FlyMode(FlyModeCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.flyModeCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;

    if (testPermission(player)) {

      if (args.length == 0) {
        toggleOwnFlyMode(player);
      } else if (args.length == 1) {
        toggleOthersFlyMode(player, Bukkit.getPlayer(args[0]));
      }

    }
    return true;
  }

  private void toggleOthersFlyMode(Player player, Player target) {

    if (player.hasPermission(Permissions.FLY_OTHER)) {

      if (!target.getAllowFlight() && !target.isFlying()) {

        target.setAllowFlight(true);
        target.setFlying(true);
        player.sendMessage(
            messageBuilder.injectParameter(flyModeCommandMessage.getSenderOtherOn(), target));
        target.sendMessage(
            messageBuilder.injectParameter(flyModeCommandMessage.getTargetOtherOn(), player));

      } else if (target.getAllowFlight() && !target.isFlying()) {

        target.setFlying(false);
        target.setAllowFlight(false);
        player.sendMessage(
            messageBuilder.injectParameter(flyModeCommandMessage.getSenderOtherOff(), target));
        target.sendMessage(
            messageBuilder.injectParameter(flyModeCommandMessage.getTargetOtherOff(), player));
      } else if (target.getAllowFlight()
          && target.isFlying()) {

        player.sendMessage(messageBuilder
            .injectParameter(flyModeCommandMessage.getTargetMustBeOnGround(), target));

      }
    } else {
      player.sendMessage(
          messageBuilder.injectParameter(flyModeCommandMessage.getNoPermissionToFlyOther()));
    }
  }

  private void toggleOwnFlyMode(Player player) {
    if (!player.getAllowFlight()
        && !player.isFlying()) {

      player.setAllowFlight(true);
      player.setFlying(true);
      player.sendMessage(messageBuilder.injectParameter(flyModeCommandMessage.getOn()));
    } else if (player.getAllowFlight()
        && !player.isFlying()) {
      player.setFlying(false);
      player.setAllowFlight(false);
      player.sendMessage(messageBuilder.injectParameter(flyModeCommandMessage.getOff()));
    }
  }
}

package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.AdminCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class AdminCommand extends CustomCommand {


  private MessageBuilder messageBuilder;
  private AdminCommandMessage adminCommandMessage;

  public AdminCommand(AdminCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.adminCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  public boolean execute(CommandSender sender, String s, String[] args) {
    Player player = (Player) sender;

    if (args.length == 0) {
      if (!player.isOp()
          && player
          .hasPermission(this.getPermission())) {

        player.setOp(true);

        player
            .sendMessage(messageBuilder.injectParameter(adminCommandMessage.getOperatorEnabled()));

      } else if (player.isOp()
          && player
          .hasPermission(this.getPermission())) {

        player.setOp(false);

        player
            .sendMessage(messageBuilder.injectParameter(adminCommandMessage.getOperatorDisabled()));
      }
    } else if (args.length == 1) {
      player.sendMessage(
          messageBuilder.injectParameter(adminCommandMessage.getPleaseUseDeopPlayer()));
    }

    return true;
  }
}

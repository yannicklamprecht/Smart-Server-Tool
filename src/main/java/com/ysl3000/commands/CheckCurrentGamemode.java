package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.CheckCurrentGamemodeCommandMessage;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class CheckCurrentGamemode extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private CheckCurrentGamemodeCommandMessage currentGamemodeCommandMessage;

  public CheckCurrentGamemode(CheckCurrentGamemodeCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.currentGamemodeCommandMessage = commandConfig;
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
        player.sendMessage(messageBuilder
            .injectParameter(currentGamemodeCommandMessage.getCurrentGamemode(), player));

      } else if (args.length == 1) {

        if (player.hasPermission(Permissions.GM_LOOKUP_OTHER)) {

          Player target = Bukkit.getPlayer(args[0]);
          if (target == null) {
            player.sendMessage(messageBuilder
                .injectParameter(currentGamemodeCommandMessage.getPlayerNotFound(), args[0]));
            return false;
          }
          sender.sendMessage(messageBuilder
              .injectParameter(currentGamemodeCommandMessage.getCurrentGamemodeOf(), target));
        } else {

          sender.sendMessage(messageBuilder.injectParameter(
              currentGamemodeCommandMessage.getYouAreNotAllowedToLookupOthersGamemode()));
        }
      }

    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

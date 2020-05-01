package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.SeenCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Seen extends CustomCommand {

  private final SeenCommandMessage seenCommandMessage;
  private final MessageBuilder messageBuilder;

  Seen(SeenCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.seenCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (testPermission(sender)) {

      Player player = (Player) sender;

      if (args.length == 1) {
        OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(args[0]);

        if (op.hasPlayedBefore()) {
          player.sendMessage(
              messageBuilder.injectParameter(seenCommandMessage.getPlayerSeenMessage(), op));
        } else {
          player.sendMessage(
              messageBuilder.injectParameter(seenCommandMessage.getNeverSeenBefore(), op));
        }
      } else {
        player.sendMessage(getUsage());
      }
    }

    return true;
  }
}

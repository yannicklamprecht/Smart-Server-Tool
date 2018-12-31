package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.CommandConfig;
import com.ysl3000.config.settings.messages.FreezeMessage;
import com.ysl3000.threads.TimeThread;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");

  private SmartPlayers smartPlayers;
  private FreezeMessage freezeMessage;
  private MessageBuilder messageBuilder;

  public Freeze(CommandConfig commandConfig, SmartPlayers smartPlayers, FreezeMessage freezeMessage,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
    this.freezeMessage = freezeMessage;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;

    if (testPermission(p)) {
      if (args.length >= 1 && args.length <= 2) {

        if (NUMBER.matcher(args[0]).matches()) {
          long time = Long.parseLong(args[0]);

          if (args.length == 1) {
            freezePlayer(p, time);
            sender.sendMessage(freezeMessage.getFreezeSelfMessage());
          } else {
            Player target = Bukkit.getPlayer(args[1]);
            freezePlayer(target, time);
            sender.sendMessage(
                messageBuilder.injectParameter(freezeMessage.getSenderFreezeMessage(), target));
            p.sendMessage(
                messageBuilder.injectParameter(freezeMessage.getTargetFreezeMessage(), p));
          }
        } else {
          sender.sendMessage(
              messageBuilder.injectParameter(freezeMessage.getParamterNotANumber(), args[0]));
        }
      } else {
        p.sendMessage(freezeMessage.getWrongInput());
      }

    }

    return true;
  }

  private void freezePlayer(final Player p, long time) {
    SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(p.getUniqueId());

    if (!smartPlayer.isFrozen()) {
      new TimeThread(time, () -> smartPlayer.setFrozen(true), () -> {
        smartPlayer.setFrozen(false);
        p.sendMessage(freezeMessage.getYouAreNotAllowedToMove());
      });
    } else {
      smartPlayer.setFrozen(false);
    }
  }
}

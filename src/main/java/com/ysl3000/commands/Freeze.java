package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.messages.commands.FreezeCommandMessage;
import com.ysl3000.threads.TimeThread;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");

  private SmartPlayers smartPlayers;
  private FreezeCommandMessage freezeCommandMessage;
  private MessageBuilder messageBuilder;

  public Freeze(FreezeCommandMessage commandConfig, SmartPlayers smartPlayers,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
    this.messageBuilder = messageBuilder;
    this.freezeCommandMessage = commandConfig;
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
            try {
              freezePlayer(p, time);
            } catch (ExecutionException e) {
              e.printStackTrace();
            }
            sender.sendMessage(freezeCommandMessage.getFreezeSelfMessage());
          } else {
            Player target = Bukkit.getPlayer(args[1]);
            try {
              freezePlayer(target, time);
            } catch (ExecutionException e) {
              e.printStackTrace();
            }
            sender.sendMessage(
                messageBuilder
                    .injectParameter(freezeCommandMessage.getSenderFreezeMessage(), target));
            p.sendMessage(
                messageBuilder.injectParameter(freezeCommandMessage.getTargetFreezeMessage(), p));
          }
        } else {
          sender.sendMessage(
              messageBuilder
                  .injectParameter(freezeCommandMessage.getParamterNotANumber(), args[0]));
        }
      } else {
        p.sendMessage(freezeCommandMessage.getWrongInput());
      }

    }

    return true;
  }

  private void freezePlayer(final Player p, long time) throws ExecutionException {
    SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(p);

      if (!smartPlayer.isFrozen()) {
        new TimeThread(time, () -> smartPlayer.setFrozen(true), () -> {
          smartPlayer.setFrozen(false);
          p.sendMessage(freezeCommandMessage.getYouAreNotAllowedToMove());
        });
      } else {
        smartPlayer.setFrozen(false);
      }

  }
}

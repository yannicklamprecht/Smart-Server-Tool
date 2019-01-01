package com.ysl3000.commands;

import com.ysl3000.config.settings.messages.commands.GamemodeCommandMessage;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class GamemodeCommand extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private final GameMode gameMode;
  private GamemodeCommandMessage gamemodeCommandMessage;

  public GamemodeCommand(GamemodeCommandMessage commandConfig, MessageBuilder messageBuilder,
      GameMode gameMode) {
    super(commandConfig);
    this.gamemodeCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
    this.gameMode = gameMode;
  }


  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {

    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {

      if (args.length == 0) {

        player.setGameMode(gameMode);
        player.sendMessage(
            messageBuilder.injectParameter(gamemodeCommandMessage.getEnterGamemodeMode(), player));

      }

    } else if (args.length == 1
        && player.hasPermission(Permissions.GAMEMODE_OTHER)) {

      Player target = Bukkit.getPlayer(args[0]);

      if (target == null) {
        sender.sendMessage(messageBuilder
            .injectParameter(gamemodeCommandMessage.getPlayerNotFound(), args[0]));
        return true;
      }

      target.setGameMode(gameMode);

      target.sendMessage(messageBuilder
          .injectParameter(gamemodeCommandMessage.getGamemodeTarget(), player,
              target.getGameMode()));

      sender.sendMessage(messageBuilder
          .injectParameter(gamemodeCommandMessage.getGamemodeSender(), target));
    }

    return true;


  }
}

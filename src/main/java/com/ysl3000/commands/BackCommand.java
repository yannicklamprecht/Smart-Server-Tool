package com.ysl3000.commands;

import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.messages.commands.BackCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class BackCommand extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private SmartPlayers smartPlayers;
  private BackCommandMessage backCommandMessage;

  BackCommand(BackCommandMessage commandConfig, MessageBuilder messageBuilder,
      SmartPlayers smartPlayers) {
    super(commandConfig);
    this.messageBuilder = messageBuilder;
    this.smartPlayers = smartPlayers;
    this.backCommandMessage = commandConfig;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    Optional<SmartPlayer> smartPlayer = smartPlayers.getPlayerByUUID(player.getUniqueId());

    AtomicBoolean success = new AtomicBoolean(false);
    smartPlayer.ifPresent(sp -> {
      if (sp.getLastLocation() == null) {
        player.sendMessage(
            messageBuilder.injectParameter(backCommandMessage.getLastLocationNotFound()));
        success.set(true);
      }
      player.teleport(sp.getLastLocation());
      success.set(true);

    });
    return success.get();
  }
}

package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.messages.commands.DoneCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.Optional;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DoneCommand extends CustomCommand {


  private SmartPlayers smartPlayers;
  private DoneCommandMessage doneCommandMessage;
  private MessageBuilder messageBuilder;


  public DoneCommand(DoneCommandMessage commandConfig, SmartPlayers smartPlayers,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
    this.doneCommandMessage = commandConfig;
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
        done(player);
        player.sendMessage(doneCommandMessage.getModmodeDisabled());
      } else if (args.length == 1) {
        Player target = player.getServer().getPlayer(args[0]);
        done(target);
        target.sendMessage(
            messageBuilder.injectParameter(doneCommandMessage.getDoneTarget(), player));
        player.sendMessage(
            messageBuilder.injectParameter(doneCommandMessage.getDoneSender(), target));
      }

    }

    return true;
  }


  private void done(Player target) {
    Optional<SmartPlayer> smartPlayer = smartPlayers.getPlayerByUUID(target.getUniqueId());
    smartPlayer.ifPresent(sp -> {
      if (sp.isMod()) {
        target.setGameMode(GameMode.SURVIVAL);
        target.getInventory().clear();
        target.getInventory().setContents(sp.getInventory());
        target.teleport(sp.getModLocation());
        sp.setMod(false);
      }
    });
  }
}

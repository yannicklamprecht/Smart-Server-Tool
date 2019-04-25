package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.messages.commands.GodCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.Optional;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class God extends CustomCommand {

  private SmartPlayers smartPlayers;
  private MessageBuilder messageBuilder;
  private GodCommandMessage godCommandMessage;


  God(GodCommandMessage commandConfig, MessageBuilder messageBuilder, SmartPlayers smartPlayers) {
    super(commandConfig);
    this.messageBuilder = messageBuilder;
    this.godCommandMessage = commandConfig;
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;
    if (testPermission(p)) {
      Optional<SmartPlayer> smartPlayer = smartPlayers.getPlayerByUUID(p.getUniqueId());
      smartPlayer.ifPresent(sp -> {
        sp.setGod(!sp.isGod());
        p.sendMessage(
            messageBuilder.injectParameter(godCommandMessage.getGodmodeMessage(), smartPlayer));
      });
    }
    return true;
  }
}

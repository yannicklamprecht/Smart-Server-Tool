package com.ysl3000.commands;

import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class BackCommand extends CustomCommand {

  private SmartPlayers smartPlayers;


  public BackCommand(CommandConfig commandConfig,SmartPlayers smartPlayers) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(player.getUniqueId());

    if (smartPlayer.getLastLocation() == null) {
      player.sendMessage("Last location not found");
      return true;
    }
    player.teleport(smartPlayer.getLastLocation());
    return true;
  }
}

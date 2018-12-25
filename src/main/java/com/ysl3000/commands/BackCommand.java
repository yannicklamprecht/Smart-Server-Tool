package com.ysl3000.commands;

import com.ysl3000.plugin.SmartPlayer;
import com.ysl3000.plugin.SmartPlayers;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class BackCommand extends CustomCommand {

  private SmartPlayers smartPlayers;


  public BackCommand(SmartPlayers smartPlayers) {
    super("back", "tp to last location", "/back", "");
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

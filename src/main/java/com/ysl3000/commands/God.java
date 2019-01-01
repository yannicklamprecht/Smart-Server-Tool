package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class God extends CustomCommand {

  private SmartPlayers smartPlayers;


  public God(CommandConfig commandConfig, SmartPlayers smartPlayers) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;
    if (testPermission(p)) {

      SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(p.getUniqueId());
      smartPlayer.setGod(!smartPlayer.isGod());

      p.sendMessage("Godmode set to " + (smartPlayer.isGod() ? "True" : "False"));

    }

    return true;
  }
}

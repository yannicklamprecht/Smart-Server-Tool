package com.ysl3000.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class TimeNight extends AbstractTimeCommand {

  public TimeNight() {
    super("tn", "Set time to night", "/tn",
        "sst.time");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    super.displayNotifyMessage(player, 18_000, "Night");
    return true;
  }
}

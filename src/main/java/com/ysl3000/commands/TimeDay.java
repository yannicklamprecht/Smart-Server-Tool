package com.ysl3000.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class TimeDay extends AbstractTimeCommand {


  public TimeDay() {
    super("td", "Set time to day", "/td", "sst.time");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    super.displayNotifyMessage(player, 0, "Day");
    return true;
  }
}

/**
 * RealTime.java
 * <p>
 * Created on , 19:14:08 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.utils.Utility;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class RealTime extends CustomCommand {


  private final Utility utility;

  public RealTime(Utility utility) {
    super("rt", "realtime", "/rt", "");
    this.utility = utility;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    player.sendMessage("Current Time "
        + ChatColor.GOLD
        + utility.getTime(System.currentTimeMillis(), "HH:mm"));
    return true;
  }
}

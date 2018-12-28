/**
 * TimeNight.java
 * <p>
 * Created on , 14:49:55 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class TimeNight extends CustomCommand {

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

    if (player.hasPermission(this.getPermission())) {
      player.getWorld().setTime(18000);
      player.sendMessage("Time set to " + ChatColor.GOLD + "Night");
    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

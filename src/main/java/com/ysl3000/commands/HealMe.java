/**
 * HealMe.java
 * <p>
 * Created on , 18:44:19 by @author Yannick Lamprecht
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
public class HealMe extends CustomCommand {

  public HealMe() {
    super("healme", "Heals you", "/healme",
        "sst.healme");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {
      player.setHealth(20.0);
      player.setFoodLevel(20);
      player.sendMessage(ChatColor.GREEN + "Healed!");
    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

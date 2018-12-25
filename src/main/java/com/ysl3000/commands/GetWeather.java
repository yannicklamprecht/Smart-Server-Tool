/**
 * GetWeather.java
 * <p>
 * Created on , 15:11:37 by @author Yannick Lamprecht
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
public class GetWeather extends CustomCommand {

  public GetWeather() {
    super("wg", "get weather", "/wg", "sst.wg");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {
      player.sendMessage("Current Weather in " + player.getWorld().getName() + " is "
          + ChatColor.GOLD + (player.getWorld().isThundering()) != null ? "rainy" : "sunny");
    } else {
      sender.sendMessage(this.getPermissionMessage());
    }
    return true;
  }
}

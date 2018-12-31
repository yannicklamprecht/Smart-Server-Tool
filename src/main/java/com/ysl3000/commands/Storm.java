/**
 * Storm.java
 * <p>
 * Created on , 15:06:54 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class Storm extends CustomCommand {


  public Storm(CommandConfig commandConfig) {
    super(commandConfig);
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;
    if (p.hasPermission(this.getPermission())) {
      p.getWorld().setStorm(true);
      p.getWorld().setThundering(true);
      p.sendMessage("Weather set to " + ChatColor.GOLD + "Storm");
    }
    return true;
  }
}

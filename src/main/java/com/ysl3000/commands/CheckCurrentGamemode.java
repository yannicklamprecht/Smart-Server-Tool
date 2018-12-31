/**
 * CheckCurrentGamemode.java
 * <p>
 * Created on , 11:53:56 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import com.ysl3000.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class CheckCurrentGamemode extends CustomCommand {

  public CheckCurrentGamemode(CommandConfig commandConfig) {
    super(commandConfig);
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {

    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {

      if (args.length == 0) {

        player.sendMessage("Current GameMode " + ChatColor.GOLD
            + player.getGameMode());

      } else if (args.length == 1) {

        if (player.hasPermission(Permissions.GM_LOOKUP_OTHER)) {

          Player target = Bukkit.getPlayer(args[0]);
          if (target == null) {
            player.sendMessage("Player not found!");
            return false;
          }
          sender.sendMessage("Current GameMode of " + ChatColor.GOLD
              + target.getDisplayName() + " "
              + target.getGameMode());
        } else {

          sender.sendMessage("You aren't allowed to lookup others gamemode");
        }
      }

    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

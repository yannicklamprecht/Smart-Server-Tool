/**
 * Seen.java
 * <p>
 * Created on , 18:55:56 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Seen extends CustomCommand {


  private final Utility utility;

  public Seen(Utility utility) {
    super("seen", "check first/last seen",
        "/seen <player>", "sst.seen");
    this.utility = utility;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (!sender.hasPermission(this.getPermission())) {
      sender.sendMessage(this.getPermissionMessage());
      return false;
    }

    Player player = (Player) sender;

    if (args.length == 0) {
      player.sendMessage("Use /seen <player>");
    } else if (args.length == 1) {
      OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);

      if (op.hasPlayedBefore()) {
        player.sendMessage(ChatColor.GREEN
            + "Player was first seen on: "
            + ChatColor.GOLD
            + utility.getTime(op.getFirstPlayed(),
            "MMM dd yyyy HH:mm")
            + "\n"
            + ChatColor.GREEN
            + "and last seen on: "
            + ChatColor.GOLD
            + utility.getTime(op.getLastPlayed(),
            "MMM dd yyyy HH:mm"));
      } else {
        player.sendMessage(ChatColor.GREEN + "Player " + ChatColor.GOLD
            + op.getName() + ChatColor.GREEN
            + " never seen before!");
      }
    } else {
      player.sendMessage(ChatColor.RED + "To much arguments");
    }

    return true;
  }
}

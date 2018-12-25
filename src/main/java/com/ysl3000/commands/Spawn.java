/**
 * Spawn.java
 * <p>
 * Created on , 17:38:21 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class Spawn extends CustomCommand {


  public Spawn() {
    super("spawn", "Teleport to Spawn", "/spawn",
        "sst.spawn");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (sender.hasPermission(this.getPermission())) {

      Player player = (Player) sender;

      Location lc = player.getWorld().getSpawnLocation();

      if (args.length == 0) {
        player.teleport(lc);

        player.sendMessage("Teleported to Spawn of world "
            + ChatColor.GOLD + player.getWorld().getName());
      } else if (args.length == 1) {
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
          player.sendMessage("Player not online");
          return true;
        }

        target.teleport(lc);

        player.sendMessage("You teleported " + target.getDisplayName()
            + " to Spawn");
        target.sendMessage("You were teleported to spawn by "
            + player.getDisplayName());
      }

    }
    return true;
  }
}

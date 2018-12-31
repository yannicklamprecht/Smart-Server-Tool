/**
 * Home.java
 * <p>
 * Created on , 18:31:37 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import com.ysl3000.utils.Permissions;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 *
 */
public class Home extends CustomCommand {


  public Home(CommandConfig commandConfig) {
    super(commandConfig);
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {
      if (player.getBedSpawnLocation() != null) {

        if (args.length == 0) {
          player.teleport(player.getBedSpawnLocation());
        } else if (args.length == 1) {

          if (player.hasPermission(Permissions.HOME_OTHER)) {

            if (player.getServer().getPlayer(args[0]).isOnline()) {
              Player target = player.getServer().getPlayer(args[0]);
              player.teleport(target.getPlayer()
                  .getBedSpawnLocation());
            } else {
              OfflinePlayer ofp = player.getServer().getOfflinePlayer(args[0]);
              player.teleport(ofp.getBedSpawnLocation());
            }
          }

        }
      } else {
        player.sendMessage("No home set");
      }
    } else {
      sender.sendMessage(this.getPermissionMessage());
    }
    return true;
  }
}

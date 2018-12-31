/**
 * SetSpawn.java
 * <p>
 * Created on , 17:35:05 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.data.WorldSpawnWrapper;
import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class SetSpawn extends CustomCommand {

  private WorldSpawnWrapper worldSpawnWrapper;


  public SetSpawn(CommandConfig commandConfig,WorldSpawnWrapper worldSpawnWrapper) {
    super(commandConfig);
    this.worldSpawnWrapper = worldSpawnWrapper;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (sender.hasPermission(this.getPermission())) {

      Player player = (Player) sender;

      player.getWorld().setSpawnLocation(player.getLocation());
      worldSpawnWrapper.setSpawnPointForWorld(player.getLocation());

      player.sendMessage("Spawn of " + player.getWorld().getName()
          + " set");

    }

    return true;
  }
}

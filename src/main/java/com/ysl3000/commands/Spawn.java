/*
  Spawn.java
  <p>
  Created on , 17:38:21 by @author Yannick Lamprecht
  <p>
  SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
  ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
  certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.SpawnCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Spawn extends CustomCommand {


  private SpawnCommandMessage spawnCommandMessage;
  private final MessageBuilder messageBuilder;

  Spawn(SpawnCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.spawnCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
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

        player.sendMessage(messageBuilder
            .injectParameter(spawnCommandMessage.getTeleportedToSpawn(), player.getWorld()));
      } else if (args.length == 1) {
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
          player.sendMessage(spawnCommandMessage.getPlayerNotOnline());
          return true;
        }

        target.teleport(lc);

        player.sendMessage(
            messageBuilder.injectParameter(spawnCommandMessage.getTeleportOtherToSpawn(), target));
        target.sendMessage(messageBuilder
            .injectParameter(spawnCommandMessage.getTeleportedBySomeoneToSpawn(), player));
      }

    }
    return true;
  }
}

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
import com.ysl3000.config.settings.messages.commands.SetSpawnCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class SetSpawn extends CustomCommand {

  private final WorldSpawnWrapper worldSpawnWrapper;


  private final SetSpawnCommandMessage setSpawnCommandMessage;
  private final MessageBuilder messageBuilder;

  SetSpawn(SetSpawnCommandMessage commandConfig,
      MessageBuilder messageBuilder,
      WorldSpawnWrapper worldSpawnWrapper) {
    super(commandConfig);
    this.setSpawnCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
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
      player.sendMessage(messageBuilder
          .injectParameter(setSpawnCommandMessage.getSetSpawnMessage(), player.getWorld()));

    }

    return true;
  }
}

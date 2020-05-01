/**
 * Kill.java
 * <p>
 * Created on , 15:41:41 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.KillCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Kill extends CustomCommand {

  private final MessageBuilder messageBuilder;
  private final KillCommandMessage killCommandMessage;

  Kill(KillCommandMessage commandConfig, MessageBuilder messageBuilder) {
    super(commandConfig);
    this.killCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {

      if (args.length == 0) {
        player.sendMessage(killCommandMessage.getNotEnoughArguments());
      } else if (args.length == 1) {

        Player target = sender.getServer().getPlayer(
            args[0]);

        if (target == null) {
          player.sendMessage(killCommandMessage.getPlayerNotFound());
          return true;
        }
        target.setHealth(0.0);
        player.sendMessage(messageBuilder
            .injectParameter(killCommandMessage.getPlayerSuccessfullyKilled(), target));
      }
    } else {
      sender.sendMessage(this.getPermissionMessage());
    }
    return true;
  }
}

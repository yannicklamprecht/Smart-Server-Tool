/**
 * PlayerLookUpIp.java
 * <p>
 * Created on , 17:27:59 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.PlayerLookUpIpCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class PlayerLookUpIp extends CustomCommand {


  private final PlayerLookUpIpCommandMessage playerLookUpIpCommandMessage;
  private final MessageBuilder messageBuilder;

  public PlayerLookUpIp(PlayerLookUpIpCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.playerLookUpIpCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (testPermission(player)) {
      if (args.length != 1) {
        return false;
      }
      // todo remove duplicate
      Player target = Bukkit.getPlayer(args[0]);

      if (target == null) {
        player.sendMessage(messageBuilder
            .injectParameter(playerLookUpIpCommandMessage.getPlayerNotFound(), args[0]));
        return true;
      }
      player.sendMessage(messageBuilder
          .injectParameter(playerLookUpIpCommandMessage.getDisplayIpAddressOfPlayerMessage(),
              target));
    }
    return true;
  }
}

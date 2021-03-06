/**
 * ModCommand.java
 * <p>
 * Created on , 14:16:34 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;

import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.messages.commands.ModCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.concurrent.ExecutionException;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author yannicklamprecht
 */
public class ModCommand extends CustomCommand {


  private final MessageBuilder messageBuilder;
  private final SmartPlayers smartPlayers;
  private final ModCommandMessage modCommandMessage;

  ModCommand(ModCommandMessage commandConfig, MessageBuilder messageBuilder,
      SmartPlayers smartPlayers) {
    super(commandConfig);
    this.modCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {

    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (!testPermission(sender)) {
      return false;
    }

    if (args.length == 0) {
      try {
        setModMode(player);
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
    }

    return true;
  }


  private void setModMode(Player target) throws ExecutionException {

    SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(target);

    if (!smartPlayer.isMod()) {

      if (target.getInventory().getContents().length == 0) {
        target.getInventory().addItem(
            new ItemStack(Material.AIR, 4));
      }

      smartPlayer.setInventory(target.getInventory()
          .getContents());
      smartPlayer.setModLocation(target.getLocation());

      target.getInventory().clear();
      target.setOp(true);
      target.setGameMode(GameMode.CREATIVE);
      target.sendMessage(messageBuilder.injectParameter(modCommandMessage.getModModeActive()));
      smartPlayer.setMod(true);
    }

  }
}

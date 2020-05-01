/**
 * Heal.java
 * <p>
 * Created on , 15:46:53 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.HealCommandMessage;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Heal extends CustomCommand {


  private final HealCommandMessage healCommandMessage;
  private final MessageBuilder messageBuilder;

  public Heal(HealCommandMessage commandConfig,
      MessageBuilder messageBuilder) {
    super(commandConfig);
    this.healCommandMessage = commandConfig;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (testPermission(sender)) {

      Player player = (Player) sender;
      if (args.length == 0) {
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.sendMessage(messageBuilder.injectParameter(healCommandMessage.getHealedSelf()));
      } else {

        if (player.hasPermission(healCommandMessage.getPermissionHealOther())) {
          List<String> playerNames = Arrays.asList(args);
          Set<Player> playerSet = Bukkit.getOnlinePlayers().stream()
              .filter(p -> playerNames.contains(p.getName())).collect(Collectors.toSet());
          if (!playerSet.isEmpty()) {
            playerSet.forEach(p -> healTarget(player, p));
            String players = String
                .join(",", playerSet.stream().map(Player::getName).collect(Collectors.toSet()));
            player.sendMessage(
                messageBuilder.injectParameter(healCommandMessage.getYouHealed(), players));
          } else {
            player.sendMessage(
                messageBuilder.injectParameter(healCommandMessage.getNoPlayerFoundWithThatName()));
          }
        }
      }
    }
    return true;
  }

  private void healTarget(Player player, Player target) {
    target.setHealth(20.0);
    target.setFoodLevel(20);
    target.sendMessage(
        messageBuilder.injectParameter(healCommandMessage.getHealedBySomeone(), player));
  }
}

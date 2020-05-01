package com.ysl3000.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix {

  private final RandColor randColor;

  public Prefix(RandColor randColor) {
    this.randColor = randColor;
  }


  public void setPrefixAndListName(Player player) {
    listName(player);
    displayName(player);

  }

  private void listName(Player player) {

    if (player.getName().length() <= 14) {
      player.setPlayerListName(ChatColor.AQUA + player.getName());
    } else {
      player.setPlayerListName(ChatColor.AQUA
          + player.getName().substring(0,
          player.getName().length() - 2));
    }

  }

  private void displayName(Player player) {
    displayName(player, player.getName());
  }

  private void displayName(Player player, String name) {
    player.setDisplayName(randColor.getNextColor() + name + ChatColor.WHITE);

  }


}

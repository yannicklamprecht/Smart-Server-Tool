package com.ysl3000.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Prefix {

  private int i = 0;

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

    while (i < 13) {

      if (i == 0) {

        player.setDisplayName(ChatColor.RED + name + ChatColor.WHITE);

        i++;
        break;
      } else if (i == 1) {

        player.setDisplayName(ChatColor.AQUA + name + ChatColor.WHITE);

        i++;
        break;
      } else if (i == 2) {
        player.setDisplayName(ChatColor.YELLOW + name + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 3) {
        player.setDisplayName(ChatColor.BLUE + name + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 4) {
        player.setDisplayName(ChatColor.DARK_AQUA + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 5) {
        player.setDisplayName(ChatColor.DARK_BLUE + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 6) {
        player.setDisplayName(ChatColor.LIGHT_PURPLE + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 7) {
        player.setDisplayName(ChatColor.DARK_GREEN + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 8) {
        player.setDisplayName(ChatColor.DARK_PURPLE + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 9) {
        player.setDisplayName(ChatColor.DARK_RED + name
            + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 10) {
        player.setDisplayName(ChatColor.GOLD + name + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 11) {
        player.setDisplayName(ChatColor.GRAY + name + ChatColor.WHITE);
        i++;
        break;
      } else if (i == 12) {
        player.setDisplayName(ChatColor.GREEN + name + ChatColor.WHITE);
        i = 0;
        break;
      }
    }
  }
}

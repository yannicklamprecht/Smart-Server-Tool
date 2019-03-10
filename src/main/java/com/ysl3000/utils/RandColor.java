package com.ysl3000.utils;

import org.bukkit.ChatColor;

/**
 * Created by ysl3000
 */
public class RandColor {

  static final ChatColor[] VALID_COLORS = {
      ChatColor.RED,
      ChatColor.AQUA,
      ChatColor.YELLOW,
      ChatColor.BLUE,
      ChatColor.DARK_AQUA,
      ChatColor.DARK_BLUE,
      ChatColor.LIGHT_PURPLE,
      ChatColor.DARK_GREEN,
      ChatColor.DARK_PURPLE,
      ChatColor.DARK_RED,
      ChatColor.GOLD,
      ChatColor.GRAY,
      ChatColor.GREEN
  };

  private int index = 0;

  ChatColor getNextColor() {
    if (index == VALID_COLORS.length) {
      index = 0;
    }
    return VALID_COLORS[index++];
  }
}

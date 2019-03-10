package com.ysl3000.events.signcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class DelSignCommand implements SignCommand {

  @Override
  public String getKey() {
    return "[del]";
  }

  @Override
  public void executeOnClick(Player player, Sign sign) {
    player.openInventory(Bukkit.createInventory(null, 9, "Disposal"));
  }

  @Override
  public void executeOnCreation(SignWrapper signWrapper) {
    signWrapper.getPlayer().sendMessage(
        ChatColor.GREEN
            + "Succesfully created a [del]-sign for disposal");
  }
}

package com.ysl3000.events.signcommands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ysl3000
 */
public class FreeSignCommand implements SignCommand {

  @Override
  public String getKey() {
    return "[free]";
  }

  @Override
  public void executeOnClick(Player player,Sign sign) {

    if (sign.getLine(2).isEmpty()) {
      return;
    }
    Material itemtype = Material.getMaterial(sign.getLine(2));
    if(itemtype==null){
      itemtype = Material.STONE;
    }
    int chestSize = Integer.parseInt(sign.getLine(3));

    Inventory iv = Bukkit.createInventory(null, chestSize,
        "Virtuell");

    for (int i = 0; i < iv.getSize(); i++) {
      ItemStack its = new ItemStack(itemtype);
      its.setAmount(64);
      iv.setItem(i, its);
    }

    player.openInventory(iv);

  }

  @Override
  public void executeOnCreation(SignWrapper e) {
    if (
        (e.getLine(2).isEmpty() || StringUtils.isNumeric(e.getLine(2)))
        ||
            (e.getLine(3).isEmpty() || !StringUtils.isNumeric(e.getLine(3)))
        ||
            Integer.parseInt(e.getLine(3)) % 9 != 0
    ) {

      e.getPlayer().sendMessage(
          ChatColor.RED + "[free]-sign creation failed");
      e.setCancelled(true);
      e.breakNaturally();
    } else {
      e.getPlayer().sendMessage(
          ChatColor.GREEN + "Succesfully created a [free]-sign");

    }

  }
}

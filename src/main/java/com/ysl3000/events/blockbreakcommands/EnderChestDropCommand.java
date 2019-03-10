package com.ysl3000.events.blockbreakcommands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ysl3000
 */
public class EnderChestDropCommand implements BlockBreakCommand {

  @Override
  public boolean isConditionFullfilled(Player player, Block block) {
    return block.getType()==Material.ENDER_CHEST;
  }

  @Override
  public void execute(Player player, Block block, Cancellable cancellable) {
    cancellable.setCancelled(true);
    block.setType(Material.AIR);
    block
        .getWorld()
        .dropItemNaturally(block.getLocation(),
            new ItemStack(Material.ENDER_CHEST));
  }
}

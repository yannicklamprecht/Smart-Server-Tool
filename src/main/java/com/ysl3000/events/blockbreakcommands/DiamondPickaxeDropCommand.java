package com.ysl3000.events.blockbreakcommands;

import com.ysl3000.config.settings.SmartSettings;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ysl3000
 */
public class DiamondPickaxeDropCommand implements BlockBreakCommand {

  private final SmartSettings smartSettings;

  public DiamondPickaxeDropCommand(SmartSettings smartSettings) {
    this.smartSettings = smartSettings;
  }

  @Override
  public boolean isConditionFullfilled(Player player, Block block) {
    return block.getType().equals(Material.DIAMOND_ORE)
        && (nextInt(smartSettings.getChance().getDiamond()) == 1
        || smartSettings.getChance().getDiamond() == 1)
        && smartSettings.getDrops().isDiamondOre();
  }

  @Override
  public void execute(Player player, Block block, Cancellable cancellable) {
    block
        .getWorld()
        .dropItem(block.getLocation(),
            new ItemStack(Material.DIAMOND_PICKAXE));
  }
}

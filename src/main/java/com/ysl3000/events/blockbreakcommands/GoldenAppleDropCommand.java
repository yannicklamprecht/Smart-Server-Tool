package com.ysl3000.events.blockbreakcommands;

import com.ysl3000.config.settings.SmartSettings;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;


/**
 * Created by ysl3000
 */
public class GoldenAppleDropCommand implements BlockBreakCommand {

  private SmartSettings smartSettings;

  public GoldenAppleDropCommand(SmartSettings smartSettings) {
    this.smartSettings = smartSettings;
  }

  @Override
  public boolean isConditionFullfilled(Player player, Block block) {
    return Tag.LEAVES.isTagged(block.getType())
        && smartSettings.getDrops().isGoldenAppleShear()
        && player.getInventory().getItemInMainHand().getType() == Material.SHEARS;
  }

  @Override
  public void execute(Player player, Block block, Cancellable cancellable) {
    block.getWorld()
        .dropItem(block.getLocation(),
            new ItemStack(Material.GOLDEN_APPLE, 1));
  }
}

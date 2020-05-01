package com.ysl3000.events.blockbreakcommands;

import com.ysl3000.config.settings.SmartSettings;
import com.ysl3000.events.BlockListener;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.ItemStack;

/**
 * Created by ysl3000
 */
public class GlassToSandDropCommand implements BlockBreakCommand {

  private final SmartSettings smartSettings;

  public GlassToSandDropCommand(SmartSettings smartSettings) {
    this.smartSettings = smartSettings;
  }

  @Override
  public boolean isConditionFullfilled(Player player, Block block) {
    return (block.getType() == Material.GLASS
        || BlockListener.STAINED_GLASS.isTagged(block.getType()))
        && (nextInt(smartSettings.getChance().getGlassSand()) == 1
        || smartSettings.getChance().getGlassSand() == 1)
        && smartSettings.getDrops().isGlassSand()
        && player.getGameMode() != GameMode.CREATIVE;
  }

  @Override
  public void execute(Player player, Block block, Cancellable cancellable) {
    block
        .getWorld()
        .dropItem(block.getLocation(),
            new ItemStack(Material.SAND, 1));
  }
}

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
public class StainedGlassDropCommand implements BlockBreakCommand {

  private SmartSettings smartSettings;

  public StainedGlassDropCommand(SmartSettings smartSettings) {
    this.smartSettings = smartSettings;
  }

  @Override
  public boolean isConditionFullfilled(Player player, Block block) {

    Material material = block.getType();

    return (material==Material.GLASS_PANE || BlockListener.STAINED_GLASS.isTagged(material))
        && (nextInt(smartSettings.getChance().getGlassPane()) == 1
        || smartSettings.getChance().getGlassPane() == 1)
        && smartSettings.getDrops().isGlassPane()
        && player.getGameMode()!= GameMode.CREATIVE;
  }

  @Override
  public void execute(Player player, Block block, Cancellable cancellable) {

      block.getWorld()
          .dropItem(
              block.getLocation(),
              new ItemStack(block.getType(), 1));


  }
}

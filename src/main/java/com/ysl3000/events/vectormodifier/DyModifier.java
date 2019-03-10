package com.ysl3000.events.vectormodifier;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Created by ysl3000
 */
public class DyModifier implements VectorModifier {

  @Override
  public void execute(Block block, Vector vector) {
    if (block.getType() == Material.IRON_BLOCK || block.getType() == Material.DIAMOND_BLOCK) {
      vector.setY(2.4D);
    }
  }

  @Override
  public int depth() {
    return 3;
  }
}

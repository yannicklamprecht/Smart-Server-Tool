package com.ysl3000.events.vectormodifier;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Created by ysl3000
 */
public class DxModifier implements VectorModifier {

  @Override
  public void execute(Block block, Vector vector) {
    if (block.getType() == Material.IRON_BLOCK) {
      vector.setX(2.4D);
    } else if (block.getType() == Material.DIAMOND_BLOCK) {
      vector.setX(-2.4D);
    }
  }

  @Override
  public int depth() {
    return 2;
  }
}

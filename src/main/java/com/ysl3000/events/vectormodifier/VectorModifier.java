package com.ysl3000.events.vectormodifier;

import org.bukkit.block.Block;
import org.bukkit.util.Vector;

/**
 * Created by ysl3000
 */
public interface VectorModifier {

  void execute(Block block, Vector vector);

  int depth();

}

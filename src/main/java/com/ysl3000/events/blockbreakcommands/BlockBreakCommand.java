package com.ysl3000.events.blockbreakcommands;

import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

/**
 * Created by ysl3000
 */
public interface BlockBreakCommand {


  boolean isConditionFullfilled(Player player, Block block);

  void execute(Player player, Block block, Cancellable cancellable);


  default int nextInt(int bound) {
    return ThreadLocalRandom.current().nextInt(bound);
  }


}

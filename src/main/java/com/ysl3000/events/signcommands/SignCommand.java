package com.ysl3000.events.signcommands;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public interface SignCommand {

  String getKey();

  void executeOnClick(Player player, Sign sign);

  default void executeOnCreation(SignWrapper signWrapper) {
    // optional implementation
  }


  interface SignWrapper {

    String getLine(int index);

    void setLine(int index, String line);

    Player getPlayer();

    void setCancelled(boolean canceled);

    void breakNaturally();
  }
}

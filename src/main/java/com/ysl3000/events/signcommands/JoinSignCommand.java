package com.ysl3000.events.signcommands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class JoinSignCommand implements SignCommand {

  @Override
  public String getKey() {
    return "[join]";
  }

  @Override
  public void executeOnClick(Player player, Sign sign) {
    if (!player.isSneaking()) {
      this.setOwningPlayerHead(player, sign.getBlock());
    }
  }


  private void setOwningPlayerHead(Player p, Block clicked) {
    Block b = p.getWorld().getBlockAt(
        new Location(p.getWorld(), clicked.getLocation().getX(),
            clicked.getLocation().getY() + 1, clicked.getLocation()
            .getZ()));

    if (b.getType() == Material.PLAYER_HEAD) {
      Skull sk = (Skull) b.getState();
      sk.setOwningPlayer(p);
      sk.update();
    } else {
      p.sendMessage("You Have to place a Skullhead first");
    }
  }
}

package com.ysl3000.events;

import com.ysl3000.utils.Permissions;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChestProtectionListener implements Listener {

  @EventHandler
  public void onChestcreate(PrepareItemCraftEvent e) {
    if (e.getInventory().getResult().getType().equals(Material.CHEST)) {
      ItemStack is = e.getInventory().getResult();
      e.getInventory().remove(e.getInventory().getResult());
      ItemMeta im = is.getItemMeta();
      im.setDisplayName(e.getViewers().get(0).getName());
      is.setItemMeta(im);
      e.getInventory().setResult(is);
      if (e.getViewers().get(0) instanceof Player) {
        ((Player) e.getViewers().get(0)).updateInventory();
      }
    }
  }

  @EventHandler
  public void onChestOpen(PlayerInteractEvent e) {

    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
        && e.getClickedBlock().getType().equals(Material.CHEST)) {

      Chest ch = (Chest) e.getClickedBlock().getState();
      if (!(ch.getInventory().getName()
          .equalsIgnoreCase(e.getPlayer().getName())
          || ch.getInventory().getName().equalsIgnoreCase("public") || e
          .getPlayer().hasPermission(Permissions.OPEN_ANY_CHEST))) {
        e.setCancelled(true);
        e.getPlayer().sendMessage(
            "This chest is protected to"
                + ch.getInventory().getName());
      }
    }
  }
}

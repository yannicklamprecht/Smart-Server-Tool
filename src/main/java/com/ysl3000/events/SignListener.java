package com.ysl3000.events;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SignListener implements Listener {

  @EventHandler
  public void onSignChange(SignChangeEvent e) {

    if (e.getLine(1).equalsIgnoreCase("[free]")) {
      if ((e.getLine(2).isEmpty() || !StringUtils.isNumeric(e.getLine(2)))
          || (e.getLine(3).isEmpty() || !StringUtils.isNumeric(e
          .getLine(3)))
          || Integer.parseInt(e.getLine(3)) % 9 != 0) {

        e.getPlayer().sendMessage(
            ChatColor.RED + "[free]-sign creation failed");
        e.setCancelled(true);
        e.getBlock().breakNaturally();
      } else {
        e.getPlayer().sendMessage(
            ChatColor.GREEN + "Succesfully created a [free]-sign");

      }
    } else if (e.getLine(1).equalsIgnoreCase("[del]")) {
      e.getPlayer().sendMessage(
          ChatColor.GREEN
              + "Succesfully created a [del]-sign for disposal");
    }
  }

  @EventHandler
  public void rclickSign(PlayerInteractEvent e) {
    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock()
        .getState() instanceof Sign) {

      Sign s = (Sign) e.getClickedBlock().getState();

      if (s.getLine(1).startsWith("[") && s.getLine(1).endsWith("]")) {
        e.setCancelled(true);
      }

      if (s.getLine(1).equalsIgnoreCase("[free]")) {
        if (s.getLine(2).isEmpty()) {
          return;
        }
        Material itemtype = Material.getMaterial(s.getLine(2));
        int chestSize = Integer.parseInt(s.getLine(3));

        Inventory iv = Bukkit.createInventory(null, chestSize,
            "Virtuell");

        for (int i = 0; i < iv.getSize(); i++) {
          ItemStack its = new ItemStack(itemtype);
          its.setAmount(64);
          iv.setItem(i, its);
        }

        e.getPlayer().openInventory(iv);
      } else if (s.getLine(1).equalsIgnoreCase("[del]")) {
        Inventory iv = Bukkit.createInventory(null, 9, "Disposal");
        e.getPlayer().openInventory(iv);
      } else if (s.getLine(1).equalsIgnoreCase("[join]")) {
        if (e.getPlayer().isSneaking()) {
          e.setCancelled(false);
          return;
        }
        this.setOwningPlayerHead(e.getPlayer(), e.getClickedBlock());
      }

    }

  }


  private void setOwningPlayerHead(Player p, Block clicked) {
    Block b = p.getWorld().getBlockAt(
        new Location(p.getWorld(), clicked.getLocation().getX(),
            clicked.getLocation().getY() + 1, clicked.getLocation()
            .getZ()));

    if (b.getType().equals(Material.PLAYER_HEAD)) {
      b.setType(Material.PLAYER_HEAD);
      Skull sk = (Skull) b.getState();
      sk.setOwningPlayer(p);
      sk.update();
    } else {
      p.sendMessage("You Have to place a Skullhead first");
    }
  }
}

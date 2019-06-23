package com.ysl3000.events;

import com.ysl3000.config.settings.Misc;
import com.ysl3000.config.settings.WorldSettings;
import com.ysl3000.utils.Permissions;
import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EntityListener implements Listener {

  private WorldSettings worldSettings;
  private Misc misc;

  EntityListener(WorldSettings worldSettings, Misc misc) {
    this.worldSettings = worldSettings;
    this.misc = misc;
  }

  @EventHandler
  public void colly(VehicleBlockCollisionEvent e) {
    if (e.getBlock().getType().equals(Material.DISPENSER)) {
      e.getVehicle().leaveVehicle();
      e.getVehicle().playEffect(EntityEffect.WOLF_SMOKE);
      e.getVehicle().remove();
      if (e.getBlock().getState() instanceof Dispenser) {
        Dispenser dp = (Dispenser) e.getBlock().getState();
        if (e.getVehicle() instanceof StorageMinecart) {
          dp.getInventory().addItem(
              new ItemStack(Material.CHEST_MINECART));
        } else if (e.getVehicle() instanceof PoweredMinecart) {
          dp.getInventory().addItem(
              new ItemStack(Material.FURNACE_MINECART));
        } else if (e.getVehicle() instanceof Minecart) {
          dp.getInventory().addItem(new ItemStack(Material.MINECART));
        }
      }
    }
  }

  @EventHandler
  public void onenderpick(EntityChangeBlockEvent event) {
    if (event.getEntityType().equals(EntityType.ENDERMAN) && worldSettings.isBlockEnder()) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onplayerdivideWater(EntityChangeBlockEvent e) {
    if ((e.getBlock().getType().equals(Material.WATER) || e.getBlock()
        .getType().equals(Material.LAVA))
        && (e.getEntity() instanceof Player)) {
      Player p = (Player) e.getEntity();

      if (!p.hasPermission(Permissions.MODIFY_BLOCK)) {
        e.setCancelled(true);
        e.getBlock().setType(e.getBlock().getType());
      }
    }
  }

  @EventHandler
  public void onEntityPP(EntityInteractEvent event) {
    if ((event.getBlock().getType().equals(Material.STONE_PRESSURE_PLATE)
        || Tag.WOODEN_PRESSURE_PLATES.isTagged(event.getBlock().getType())) && misc
        .isSavePlayerPressPlate()) {
      event.setCancelled((!(event.getEntity() instanceof Player)) || event
          .isCancelled());
    }

  }

  @EventHandler
  public void playerdamage(EntityDamageEvent e) {

    if (e.getEntityType().equals(EntityType.PLAYER)) {

      boolean hasShoesWithNameJump = false;

      PlayerInventory inventory = ((Player) e.getEntity()).getInventory();
      if (inventory != null && inventory.getBoots() != null) {
        ItemStack boots = inventory.getBoots();
        hasShoesWithNameJump = (boots.hasItemMeta()
            && boots.getItemMeta().hasDisplayName()
            && boots.getItemMeta().getDisplayName().equalsIgnoreCase("Jump"));

      }
      e.setCancelled((hasShoesWithNameJump
          && e.getCause() == EntityDamageEvent.DamageCause.FALL)
          || ((Player) e.getEntity()).getGameMode().equals(
          GameMode.CREATIVE));

    }

  }

  @EventHandler
  public void explode(EntityExplodeEvent event) {

    if (event.getEntityType() == EntityType.CREEPER) {
      if (worldSettings.isBlockCreeper()) {
        event.setCancelled(true);
      }
    } else {
      if (worldSettings.isPreventTnt()) {
        event.setCancelled(true);
      }
    }
  }

  @EventHandler
  public void onPlayerCreatureSpawnerChange(PlayerInteractEvent e) {
    if (e.getClickedBlock() == null) {
      return;
    }
    if (e.getClickedBlock().getType() == Material.SPAWNER
        && e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getPlayer()
        .hasPermission(Permissions.CAN_CAHNGE_SPAWNER_TYPE)
        && e.getClickedBlock().getState() instanceof CreatureSpawner) {

      CreatureSpawner cs = (CreatureSpawner) e.getClickedBlock()
          .getState();

      EntityType next = getNext(cs.getSpawnedType());

      cs.setSpawnedType(next);
      e.getPlayer().sendMessage("Mobtype set to " + next);
    }
  }

  private EntityType getNext(EntityType entityType) {
    int index = entityType.ordinal();
    index++;

    if (index > EntityType.values().length) {
      index = 0;
    }
    return EntityType.values()[index];
  }

  @EventHandler
  public void onSmoke(PlayerMoveEvent e) {

    Location l = new Location(e.getPlayer().getWorld(), 0, e.getPlayer()
        .getWorld().getHighestBlockYAt(0, 0), 0);

    if (e.getPlayer().getLocation().distance(l) < 10) {
      e.getPlayer().getWorld()
          .playEffect(l, Effect.MOBSPAWNER_FLAMES, 0, 0);
    }

  }

}

package com.ysl3000.events;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.plugin.SmartServerTool;
import com.ysl3000.utils.ConfigFactorizer;
import com.ysl3000.utils.Permissions;

public class EntityListener implements Listener {
	private JavaPlugin plugin;

	public EntityListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
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
							new ItemStack(Material.STORAGE_MINECART));
				} else if (e.getVehicle() instanceof PoweredMinecart) {
					dp.getInventory().addItem(
							new ItemStack(Material.POWERED_MINECART));
				} else if (e.getVehicle() instanceof Minecart) {
					dp.getInventory().addItem(new ItemStack(Material.MINECART));
				}
			}
		}
	}

	@EventHandler
	public void onenderpick(EntityChangeBlockEvent event) {
		event.setCancelled(event.getEntityType().equals(EntityType.ENDERMAN) ? ConfigFactorizer
				.createAndReturn(this.plugin).isBender() : event.isCancelled());
	}

	@EventHandler
	public void onplayerdivideWater(EntityChangeBlockEvent e) {
		if ((e.getBlock().getType().equals(Material.WATER) || e.getBlock()
				.getType().equals(Material.LAVA))
				&& (e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();

			if (!p.hasPermission(Permissions.modifyBlock)) {
				e.setCancelled(true);
				e.getBlock().setType(e.getBlock().getType());
			}
		}
	}

	@EventHandler
	public void onEntityPP(EntityInteractEvent event) {

		if (event.getBlock().getType().equals(Material.STONE_PLATE)
				|| event.getBlock().getType().equals(Material.WOOD_PLATE)) {

			if (ConfigFactorizer.createAndReturn(this.plugin)
					.isPlayerPressPlate()) {

				event.setCancelled((event.getEntity() instanceof Player) ? event
						.isCancelled() : true);
			}
		}
	}

	@EventHandler
	public void playerdammage(EntityDamageEvent e) {

		if (e.getEntityType().equals(EntityType.PLAYER)) {

			boolean hasShoesWithNameJump = false;
			try {
				hasShoesWithNameJump = ((Player) e.getEntity()).getInventory()
						.getBoots().getItemMeta().getDisplayName()
						.equalsIgnoreCase("Jump");
			} catch (NullPointerException eN) {

			}
			e.setCancelled((hasShoesWithNameJump && e.getCause().name()
					.equalsIgnoreCase("fall"))
					|| ((Player) e.getEntity()).getGameMode().equals(
							GameMode.CREATIVE));

		}

	}

	@EventHandler
	public void Explode(EntityExplodeEvent event) {
		event.setCancelled(event.getEntityType().equals(EntityType.CREEPER) ? ConfigFactorizer
				.createAndReturn(this.plugin).isBcreeper() : ConfigFactorizer
				.createAndReturn(this.plugin).isTntsave());
	}

	@EventHandler
	public void onPlayerCreatureSpawnerChange(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null)
			return;
		if ((e.getClickedBlock().getType().equals(Material.MOB_SPAWNER))
				&& (e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
			if ((e.getPlayer().hasPermission(Permissions.canChangeSpawnerType))
					&& ((e.getClickedBlock().getState() instanceof CreatureSpawner))) {
				CreatureSpawner cs = (CreatureSpawner) e.getClickedBlock()
						.getState();

				if (cs.getSpawnedType().equals(EntityType.ZOMBIE))
					cs.setSpawnedType(EntityType.BLAZE);
				else if (cs.getSpawnedType().equals(EntityType.BLAZE)) {
					cs.setSpawnedType(EntityType.CAVE_SPIDER);
				} else if (cs.getSpawnedType().equals(EntityType.CAVE_SPIDER))
					cs.setSpawnedType(EntityType.CHICKEN);
				else if (cs.getSpawnedType().equals(EntityType.CHICKEN))
					cs.setSpawnedType(EntityType.COW);
				else if (cs.getSpawnedType().equals(EntityType.COW))
					cs.setSpawnedType(EntityType.CREEPER);
				else if (cs.getSpawnedType().equals(EntityType.CREEPER)) {
					cs.setSpawnedType(EntityType.ENDER_DRAGON);
				} else if (cs.getSpawnedType().equals(EntityType.ENDER_DRAGON))
					cs.setSpawnedType(EntityType.ENDERMAN);
				else if (cs.getSpawnedType().equals(EntityType.ENDERMAN))
					cs.setSpawnedType(EntityType.GHAST);
				else if (cs.getSpawnedType().equals(EntityType.GHAST))
					cs.setSpawnedType(EntityType.GIANT);
				else if (cs.getSpawnedType().equals(EntityType.GIANT)) {
					cs.setSpawnedType(EntityType.MAGMA_CUBE);
				} else if (cs.getSpawnedType().equals(EntityType.MAGMA_CUBE)) {
					cs.setSpawnedType(EntityType.MUSHROOM_COW);
				} else if (cs.getSpawnedType().equals(EntityType.MUSHROOM_COW))
					cs.setSpawnedType(EntityType.PIG);
				else if (cs.getSpawnedType().equals(EntityType.PIG)) {
					cs.setSpawnedType(EntityType.PIG_ZOMBIE);
				} else if (cs.getSpawnedType().equals(EntityType.PIG_ZOMBIE))
					cs.setSpawnedType(EntityType.SHEEP);
				else if (cs.getSpawnedType().equals(EntityType.SHEEP)) {
					cs.setSpawnedType(EntityType.SILVERFISH);
				} else if (cs.getSpawnedType().equals(EntityType.SILVERFISH))
					cs.setSpawnedType(EntityType.SKELETON);
				else if (cs.getSpawnedType().equals(EntityType.SKELETON))
					cs.setSpawnedType(EntityType.SLIME);
				else if (cs.getSpawnedType().equals(EntityType.SLIME))
					cs.setSpawnedType(EntityType.SNOWMAN);
				else if (cs.getSpawnedType().equals(EntityType.SNOWMAN))
					cs.setSpawnedType(EntityType.SPIDER);
				else if (cs.getSpawnedType().equals(EntityType.SPIDER))
					cs.setSpawnedType(EntityType.SQUID);
				else if (cs.getSpawnedType().equals(EntityType.SQUID))
					cs.setSpawnedType(EntityType.VILLAGER);
				else if (cs.getSpawnedType().equals(EntityType.VILLAGER))
					cs.setSpawnedType(EntityType.WOLF);
				else if (cs.getSpawnedType().equals(EntityType.WOLF)) {
					cs.setSpawnedType(EntityType.ZOMBIE);
				}

				e.getPlayer().sendMessage(
						"Mobtype set to " + cs.getCreatureTypeName());
			}
		}
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

package com.ysl3000.events;

import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
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
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
import org.bukkit.inventory.ItemStack;

import com.ysl3000.permissions.Permissions;
import com.ysl3000.plugin.SmartServerTool;
import com.ysl3000.utils.SmartController;

public class EntityListener implements Listener {

	public EntityListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
	public void onPlayerDeath(PlayerDeathEvent event) throws Exception {
		if (SmartServerTool.getConfigLoader().isXpsave()) {
			event.setDroppedExp(0);
			event.setNewExp((int) event.getEntity().getPlayer()
					.getTotalExperience());
		}
	}

	@EventHandler
	public void onenderpick(EntityChangeBlockEvent event) {
		event.setCancelled(event.getEntityType().equals(EntityType.ENDERMAN) ? SmartServerTool
				.getConfigLoader().isBender() : event.isCancelled());
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

		if ((event.getBlock().getTypeId() == 70)
				|| (event.getBlock().getTypeId() == 72)) {

			if (SmartServerTool.getConfigLoader().isPlayerPressPlate()) {

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
			e.setCancelled(SmartController.getSmartControler().getHashmaps()
					.getSmartPLayers().get((Player) e.getEntity()).isGod()
					|| (hasShoesWithNameJump && e.getCause().name()
							.equalsIgnoreCase("fall"))
					|| ((Player) e.getEntity()).getGameMode().equals(
							GameMode.CREATIVE));

		}

	}

	@EventHandler
	public void nofood(FoodLevelChangeEvent e) {
		e.setCancelled(e.getEntityType().equals(EntityType.PLAYER) ? SmartController
				.getSmartControler().getHashmaps().getSmartPLayers()
				.get((Player) e.getEntity()).isGod()
				: e.isCancelled());
	}

	@EventHandler
	public void Explode(EntityExplodeEvent event) {
		event.setCancelled(event.getEntityType().equals(EntityType.CREEPER) ? SmartServerTool
				.getConfigLoader().isBcreeper() : SmartServerTool
				.getConfigLoader().isTntsave());
	}

	@EventHandler
	public void onPlayerCreatureSpawnerChange(PlayerInteractEvent e) {

		if (e.getClickedBlock() == null)
			return;
		if (e.getClickedBlock().getType().equals(Material.MOB_SPAWNER)
				&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (e.getPlayer().hasPermission(Permissions.canChangeSpawnerType)) {
				if ((e.getClickedBlock().getState() instanceof CreatureSpawner)) {

					CreatureSpawner cs = (CreatureSpawner) e.getClickedBlock()
							.getState();

					if (cs.getSpawnedType().equals(EntityType.ZOMBIE)) {
						cs.setSpawnedType(EntityType.BLAZE);
					} else if (cs.getSpawnedType().equals(EntityType.BLAZE)) {
						cs.setSpawnedType(EntityType.CAVE_SPIDER);
					} else if (cs.getSpawnedType().equals(
							EntityType.CAVE_SPIDER)) {
						cs.setSpawnedType(EntityType.CHICKEN);
					} else if (cs.getSpawnedType().equals(EntityType.CHICKEN)) {
						cs.setSpawnedType(EntityType.COW);
					} else if (cs.getSpawnedType().equals(EntityType.COW)) {
						cs.setSpawnedType(EntityType.CREEPER);
					} else if (cs.getSpawnedType().equals(EntityType.CREEPER)) {
						cs.setSpawnedType(EntityType.ENDER_DRAGON);
					} else if (cs.getSpawnedType().equals(
							EntityType.ENDER_DRAGON)) {
						cs.setSpawnedType(EntityType.ENDERMAN);
					} else if (cs.getSpawnedType().equals(EntityType.ENDERMAN)) {
						cs.setSpawnedType(EntityType.GHAST);
					} else if (cs.getSpawnedType().equals(EntityType.GHAST)) {
						cs.setSpawnedType(EntityType.GIANT);
					} else if (cs.getSpawnedType().equals(EntityType.GIANT)) {
						cs.setSpawnedType(EntityType.MAGMA_CUBE);
					} else if (cs.getSpawnedType()
							.equals(EntityType.MAGMA_CUBE)) {
						cs.setSpawnedType(EntityType.MUSHROOM_COW);
					} else if (cs.getSpawnedType().equals(
							EntityType.MUSHROOM_COW)) {
						cs.setSpawnedType(EntityType.PIG);
					} else if (cs.getSpawnedType().equals(EntityType.PIG)) {
						cs.setSpawnedType(EntityType.PIG_ZOMBIE);
					} else if (cs.getSpawnedType()
							.equals(EntityType.PIG_ZOMBIE)) {
						cs.setSpawnedType(EntityType.SHEEP);
					} else if (cs.getSpawnedType().equals(EntityType.SHEEP)) {
						cs.setSpawnedType(EntityType.SILVERFISH);
					} else if (cs.getSpawnedType()
							.equals(EntityType.SILVERFISH)) {
						cs.setSpawnedType(EntityType.SKELETON);
					} else if (cs.getSpawnedType().equals(EntityType.SKELETON)) {
						cs.setSpawnedType(EntityType.SLIME);
					} else if (cs.getSpawnedType().equals(EntityType.SLIME)) {
						cs.setSpawnedType(EntityType.SNOWMAN);
					} else if (cs.getSpawnedType().equals(EntityType.SNOWMAN)) {
						cs.setSpawnedType(EntityType.SPIDER);
					} else if (cs.getSpawnedType().equals(EntityType.SPIDER)) {
						cs.setSpawnedType(EntityType.SQUID);
					} else if (cs.getSpawnedType().equals(EntityType.SQUID)) {
						cs.setSpawnedType(EntityType.VILLAGER);
					} else if (cs.getSpawnedType().equals(EntityType.VILLAGER)) {
						cs.setSpawnedType(EntityType.WOLF);
					} else if (cs.getSpawnedType().equals(EntityType.WOLF)) {
						cs.setSpawnedType(EntityType.ZOMBIE);
					}

					e.getPlayer().sendMessage(
							"Mobtype set to " + cs.getCreatureTypeName());
				}
			}
		}

	}
}

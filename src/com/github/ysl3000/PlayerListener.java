package com.github.ysl3000;

import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	public PlayerListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {

		event.getPlayer().teleport(
				event.getPlayer().getBedSpawnLocation() == null ? event
						.getPlayer().getWorld().getSpawnLocation() : event
						.getPlayer().getBedSpawnLocation());

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) throws Exception {

		if (ConfigLoader.isXpsave()) {
			event.setDroppedExp(0);

			event.setNewExp((int) event.getEntity().getPlayer()
					.getTotalExperience());
		}

		event.getEntity().setAllowFlight(
				HashmapHandler.isFlyStatus(event.getEntity()));
		event.getEntity().setFlying(
				HashmapHandler.isFlyStatus(event.getEntity()));
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {

		Player player = event.getPlayer();
		Entity etarget = event.getRightClicked();

		if (!(etarget instanceof Player)) {

			return;

		}

		Player target = (Player) etarget;

		if (player.hasPermission("sst.info")) {

			player.sendMessage("Infos of: " + target.getDisplayName());
			player.sendMessage("Foodlevel: " + target.getFoodLevel());
			player.sendMessage("Health: " + target.getHealth());
			player.sendMessage("Ip: " + target.getAddress());
			player.sendMessage("Op-status: " + target.isOp());
			player.sendMessage("Gamemode: " + target.getGameMode());
			player.sendMessage("XP: " + target.getTotalExperience());
			player.sendMessage("XP-Level: " + target.getExpToLevel());

		}

	}

	@EventHandler
	public void onbreak(BlockBreakEvent event) {

		Player player = event.getPlayer();

		event.setCancelled(false);

		if (player.hasPermission("sst.break")) {

			BlockDrops(event);

		} else {

			event.setCancelled(ConfigLoader.isBlockbreak());
			BlockDrops(event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onbuild(BlockPlaceEvent event) throws Exception {

		event.setCancelled(event.getPlayer().hasPermission("sst.build") ? false
				: ConfigLoader.isBbuild());
	}

	@EventHandler
	public void Explode(EntityExplodeEvent event) {

		event.setCancelled(event.getEntityType().equals(EntityType.CREEPER) ? ConfigLoader
				.isBcreeper() : ConfigLoader.isTntsave());

	}

	@EventHandler
	public void onenderpick(EntityChangeBlockEvent event) {

		event.setCancelled(event.getEntityType().equals(EntityType.ENDERMAN) ? ConfigLoader
				.isBender() : event.isCancelled());

	}

	@EventHandler
	public void onplayerdive(PlayerToggleSneakEvent event) {

		Player player = event.getPlayer();

		if (player.getGameMode().equals(GameMode.SURVIVAL)) {
			if (player.getRemainingAir() <= 5) {
				if (player.getInventory().getHelmet() != null) {

					short dur = player.getInventory().getHelmet()
							.getDurability();
					player.setRemainingAir(300);

					short newdur = (short) (dur + 5);
					player.getInventory().getHelmet().setDurability(newdur);
					player.sendMessage(ChatColor.GOLD + "Air recharged!!");

				} else {

					player.sendMessage(ChatColor.RED
							+ "you have no helmet to charge your breath");
				}
			} else {

				return;

			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onplayerrb(PlayerInteractEvent event) {
		if (event.getPlayer().hasPermission("sst.interact")
				|| (!ConfigLoader.isInteract())) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
					&& (event.getClickedBlock().getType().equals(Material.BED) || event
							.getClickedBlock().getType()
							.equals(Material.BED_BLOCK))) {

				event.getPlayer().setBedSpawnLocation(
						event.getClickedBlock().getLocation());
				event.getPlayer().sendMessage(
						ChatColor.BLUE + "Bedspawn location set!");

			}
		} else {
			return;
		}

	}

	@EventHandler
	public void Interact(PlayerInteractEvent event) {

		event.setCancelled(event.getPlayer().hasPermission("sst.interact") ? event
				.isCancelled() : ConfigLoader.isInteract());

	}

	@EventHandler
	public void onblockburn(BlockBurnEvent event) {

		event.setCancelled(ConfigLoader.isBlockburn());

	}

	@EventHandler
	public void onblockig(BlockIgniteEvent event) {

		if (event.getCause().equals(IgniteCause.LAVA)) {

			event.setCancelled(ConfigLoader.isLavaspread());
		} else if (event.getCause().equals(IgniteCause.FLINT_AND_STEEL)) {
			event.setCancelled(ConfigLoader.isFlint_and_steal_spread());
		} else if (event.getCause().equals(IgniteCause.LIGHTNING)) {
			event.setCancelled(ConfigLoader.isLightning_spread());
		} else if (event.getCause().equals(IgniteCause.SPREAD)) {
			event.setCancelled(ConfigLoader.isNormalspread());
		}
	}

	@EventHandler
	public void playerteleport(PlayerTeleportEvent event) {

		HashmapHandler.setLastLocation(event.getPlayer(), event.getFrom());
		HashmapHandler.setCurrentLocation(event.getPlayer(), event.getTo());

	}

	@EventHandler
	public void BlockDrops(BlockBreakEvent event) {

		if (!event.isCancelled()) {

			Random rando = new Random();
			if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {

				if (rando.nextInt(ConfigLoader.getDiamondDropChance()) == 1
						|| ConfigLoader.getDiamondDropChance() == 1) {
					if (ConfigLoader.isDiamondDrop()) {
						event.getBlock()
								.getWorld()
								.dropItem(event.getBlock().getLocation(),
										new ItemStack(Material.DIAMOND_PICKAXE));
					}
				}

			} else if (event.getBlock().getType().equals(Material.LEAVES)) {

				if (rando.nextInt(ConfigLoader.getAppleDropChance()) == 1
						|| ConfigLoader.getAppleDropChance() == 1) {
					if (ConfigLoader.isappleDrop()) {
						event.getBlock()
								.getWorld()
								.dropItem(event.getBlock().getLocation(),
										new ItemStack(Material.GOLDEN_APPLE, 1));
					}

				}

			} else if (event.getBlock().getTypeId() == 102) {
				if (rando.nextInt(ConfigLoader.getGlassPaneDropChance()) == 1
						|| ConfigLoader.getGlassPaneDropChance() == 1) {
					if (ConfigLoader.isGlassPaneDrop()) {
						event.getBlock()
								.getWorld()
								.dropItem(event.getBlock().getLocation(),
										new ItemStack(102, 1));
					}
				}

			} else if (event.getBlock().getType().equals(Material.GLASS)) {
				if (rando.nextInt(ConfigLoader.getGlassSandDropChance()) == 1
						|| ConfigLoader.getGlassSandDropChance() == 1) {
					if (ConfigLoader.isGlassSandDrop()) {
						event.getBlock()
								.getWorld()
								.dropItem(event.getBlock().getLocation(),
										new ItemStack(Material.SAND, 1));
					}

				}
			} else if (event.getBlock().getType().equals(Material.ENDER_CHEST)) {

				event.setCancelled(true);
				event.getBlock().setType(Material.AIR);

				event.getBlock()
						.getWorld()
						.dropItemNaturally(event.getBlock().getLocation(),
								new ItemStack(Material.ENDER_CHEST));
			}

		}

	}

	@EventHandler
	public void onEntityPP(EntityInteractEvent event) {

		if ((event.getBlock().getTypeId() == 70)
				|| (event.getBlock().getTypeId() == 72)) {

			if (ConfigLoader.isPlayerPressPlate()) {

				event.setCancelled(event.getEntity() instanceof Player ? event
						.isCancelled() : true);

			}

		}

	}

	@EventHandler
	public void onPhysics(BlockPhysicsEvent e) {

		if (e.getBlock().getType().equals(Material.TRAP_DOOR)) {
			e.setCancelled(ConfigLoader.getPhysicsTrapdoor());
		}
		if (e.getBlock().getType().equals(Material.TORCH)) {
			e.setCancelled(ConfigLoader.getPhysicsTorch());
		}
		if (e.getBlock().getType().equals(Material.SAND)) {
			e.setCancelled(ConfigLoader.getPhysicsSand());
		}
		if (e.getBlock().getType().equals(Material.GRAVEL)) {
			e.setCancelled(ConfigLoader.getPhysicsGravel());
		}

	}

	@EventHandler
	public void vehiclecoll(VehicleDestroyEvent e) {
		e.setCancelled(e.getVehicle().getType().equals(Material.BOAT)
				|| e.getVehicle().getType().equals(Material.MINECART)
				&& e.getAttacker() == null ? true : e.isCancelled());
	}

	@EventHandler
	public void playerdammage(EntityDamageEvent e) {

		e.setCancelled(e.getEntityType().equals(EntityType.PLAYER) ? HashmapHandler
				.isGod((Player) e.getEntity()) : e.isCancelled());

	}

	@EventHandler
	public void chatHandler(AsyncPlayerChatEvent e) {

		if (e.getPlayer().hasPermission("sst.chat")) {
			e.setCancelled(e.isCancelled());
		} else {
			e.setCancelled(true);
			e.getPlayer().sendMessage(SmartServerTool.noperms);
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		e.setCancelled(e.getPlayer().hasPermission("sst.move") ? e
				.isCancelled() : true);
	}

	

}

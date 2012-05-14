package com.github.ysl3000;

import java.util.HashMap;
import java.util.Random;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	private static HashMap<Player, Location> LastL = new HashMap<Player, Location>();

	public static HashMap<Player, Location> getlocation() {

		return LastL;

	}

	public static void setLastL(HashMap<Player, Location> LastLoc) {
		LastL = LastLoc;
	}

	public PlayerListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {

		Player player = event.getPlayer();

		if (player.getBedSpawnLocation() == null) {

			player.teleport(player.getWorld().getSpawnLocation());

		} else if (player.getBedSpawnLocation() != null) {

			player.teleport(player.getBedSpawnLocation());
		}

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) throws Exception {

		if (ConfigLoader.isXpsave()) {
			event.setDroppedExp(0);

			event.setNewExp((int) event.getEntity().getPlayer()
					.getTotalExperience());
		}

		return;

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

		}

	}

	@EventHandler
	public void onbreak(BlockBreakEvent event) {

		Player player = event.getPlayer();

		event.setCancelled(false);

		if (player.hasPermission("sst.break")) {

			Random rando = new Random();

			if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {

				if (rando.nextInt(ConfigLoader.getDiamondDropChance()) == 1) {
					if (ConfigLoader.getDiamondDrop()) {
						event.getBlock()
								.getWorld()
								.dropItem(event.getBlock().getLocation(),
										new ItemStack(Material.DIAMOND_PICKAXE));
					}
				}

			} else if (event.getBlock().getType().equals(Material.LEAVES)) {

				if (ConfigLoader.getappleDrop()) {
					(event.getBlock().getWorld()).dropItemNaturally(event
							.getBlock().getLocation(), new ItemStack(
							Material.APPLE, 1));

					(event.getBlock().getWorld()).dropItemNaturally(event
							.getBlock().getLocation(), new ItemStack(
							Material.GOLDEN_APPLE, 1));
				}

			} else if (event.getBlock().getTypeId() == 102) {

				if (ConfigLoader.getGlasspaneDrop()) {
					event.getBlock()
							.getWorld()
							.dropItem(event.getBlock().getLocation(),
									new ItemStack(102, 1));
				}

			} else if (event.getBlock().getType().equals(Material.GLASS)) {

				if (ConfigLoader.getGlassSandDrop()) {
					if (rando.nextInt(ConfigLoader.getDropchance()) == 1) {
						event.getBlock()
								.getWorld()
								.dropItemNaturally(
										event.getBlock().getLocation(),
										new ItemStack(Material.SAND, 1));
					}

				}
			}

		} else {

			event.setCancelled(ConfigLoader.isBlockbreak());

		}
	}

	@EventHandler
	public void onbuild(BlockPlaceEvent event) throws Exception {

		Player player = event.getPlayer();

		if (player.hasPermission("sst.build")) {

			event.setCancelled(false);

		} else {
			event.setCancelled(ConfigLoader.isBbuild());
		}

	}

	@EventHandler
	public void Explode(EntityExplodeEvent event) {

		if (event.getEntityType().equals(EntityType.CREEPER)) {
			event.setCancelled(ConfigLoader.isBcreeper());
		} else if (!event.getEntity().equals(EntityType.CREEPER)) {
			event.setCancelled(ConfigLoader.isTntsave());
		}

	}

	@EventHandler
	public void onenderpick(EntityChangeBlockEvent event) {

		if (!event.getEntityType().equals(EntityType.ENDERMAN)) {
			return;
		}
		event.setCancelled(ConfigLoader.isBender());

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

	@EventHandler(priority = EventPriority.HIGH)
	public void onplayerrb(PlayerInteractEvent event) {

		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& (event.getClickedBlock().getType().equals(Material.BED) || event
						.getClickedBlock().getType().equals(Material.BED_BLOCK))) {

			Player player = event.getPlayer();

			player.setPlayerTime(18000L, false);

			event.getPlayer().setBedSpawnLocation(
					event.getClickedBlock().getLocation());
			event.getPlayer().sendMessage(
					ChatColor.BLUE + "Bedspawn location set!");
			player.resetPlayerTime();
		} else {
			return;
		}

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
	public void onBoatbreak(VehicleDestroyEvent event) {

		if (event.getVehicle().getType().equals(EntityType.BOAT)) {

			if (event.getVehicle().getLastDamageCause().getEntityType()
					.equals(EntityType.PLAYER)) {
				event.getVehicle()
						.getLocation()
						.getWorld()
						.dropItem(event.getVehicle().getLocation(),
								new ItemStack(Material.BOAT, 1));
				event.setCancelled(true);
				event.getVehicle().remove();
			} else if (!event.getVehicle().getLastDamageCause().getEntityType()
					.equals(EntityType.PLAYER)) {
				event.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void playerteleport(PlayerTeleportEvent event) {

		LastL.put(event.getPlayer(), event.getFrom());

	}

}

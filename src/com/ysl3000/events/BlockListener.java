package com.ysl3000.events;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.ysl3000.permissions.Permissions;
import com.ysl3000.plugin.SmartServerTool;

public class BlockListener implements Listener {

	public BlockListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onbreak(BlockBreakEvent event) {
		event.setCancelled(!event.getPlayer().hasPermission(
				Permissions.modifyBlock)
				|| SmartServerTool.getConfigLoader()
						.getNonPermission() ? false : true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onbuild(BlockPlaceEvent event) {
		event.setCancelled(!event.getPlayer().hasPermission(
				Permissions.modifyBlock)
				&& !SmartServerTool.getConfigLoader()
						.getNonPermission());
	}

	@EventHandler
	public void onPhysics(BlockPhysicsEvent e) {
		if (e.getBlock().getType().equals(Material.TRAP_DOOR)) {
			e.setCancelled(SmartServerTool
					.getConfigLoader().getPhysicsTrapdoor());
		}
		if (e.getBlock().getType().equals(Material.TORCH)) {
			e.setCancelled(SmartServerTool
					.getConfigLoader().getPhysicsTorch());
		}
		if (e.getBlock().getType().equals(Material.SAND)) {
			e.setCancelled(SmartServerTool
					.getConfigLoader().getPhysicsSand());
		}
		if (e.getBlock().getType().equals(Material.GRAVEL)) {
			e.setCancelled(SmartServerTool
					.getConfigLoader().getPhysicsGravel());
		}
		if (e.getBlock().getType().equals(Material.DIAMOND_BLOCK)) {
			if (e.getBlock().getBlockPower() == 1) {
				e.getBlock()
						.getWorld()
						.getBlockAt(e.getBlock().getLocation().getBlockX(),
								e.getBlock().getLocation().getBlockY() + 5,
								e.getBlock().getLocation().getBlockZ())
						.setType(Material.WATER);
			} else {
				e.getBlock()
						.getWorld()
						.getBlockAt(e.getBlock().getLocation().getBlockX(),
								e.getBlock().getLocation().getBlockY() + 5,
								e.getBlock().getLocation().getBlockZ())
						.setType(Material.AIR);
			}
		}

	}

	@EventHandler
	public void spawny(BlockDispenseEvent e) {

		if (e.getItem().getType().equals(Material.MINECART)
				|| e.getItem().getType().equals(Material.STORAGE_MINECART)
				|| e.getItem().getType().equals(Material.POWERED_MINECART)) {
			if (e.getBlock().getState() instanceof Dispenser) {
				Dispenser dp = (Dispenser) e.getBlock().getState();
				dp.getInventory().addItem(e.getItem());
			}
		}
	}

	@EventHandler
	public void onblockburn(BlockBurnEvent event) {
		event.setCancelled(SmartServerTool
				.getConfigLoader().isBlockburn());
	}

	@EventHandler
	public void onblockig(BlockIgniteEvent event) {
		event.setCancelled(event.getCause().equals(IgniteCause.LAVA) ? SmartServerTool.getConfigLoader().isLavaspread()
				: event.getCause().equals(IgniteCause.LIGHTNING) ? SmartServerTool.getConfigLoader()
						.isLightning_spread()
						: event.getCause().equals(IgniteCause.SPREAD) ? SmartServerTool.getConfigLoader()
								.isNormalspread()
								: false);
	}

	@EventHandler
	public void BlockDrops(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			if (event.getPlayer().getItemInHand().getEnchantments()
					.containsKey(new EnchantmentWrapper(33)))
				return;
			Random rando = new Random();
			if (event.getBlock().getType().equals(Material.DIAMOND_ORE)
					&& (rando.nextInt(SmartServerTool
							.getConfigLoader().getDiamondDropChance()) == 1 || SmartServerTool.getConfigLoader()
							.getDiamondDropChance() == 1)
					&& SmartServerTool.getConfigLoader()
							.isDiamondDrop()) {
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.DIAMOND_PICKAXE));
			} else if (event.getBlock().getType().equals(Material.LEAVES)
					&& SmartServerTool.getConfigLoader()
							.isAppleShear()
					&& event.getPlayer().getItemInHand().getType()
							.equals(Material.SHEARS)) {
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.GOLDEN_APPLE, 1));

			} else if (event.getBlock().getTypeId() == 102
					&& (rando.nextInt(SmartServerTool
							.getConfigLoader().getGlassPaneDropChance()) == 1 || SmartServerTool.getConfigLoader()
							.getGlassPaneDropChance() == 1)
					&& SmartServerTool.getConfigLoader()
							.isGlassPaneDrop()) {

				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(102, 1));

			} else if (event.getBlock().getType().equals(Material.GLASS)
					&& (rando.nextInt(SmartServerTool
							.getConfigLoader().getGlassSandDropChance()) == 1 || SmartServerTool.getConfigLoader()
							.getGlassSandDropChance() == 1)
					&& SmartServerTool.getConfigLoader()
							.isGlassSandDrop()) {
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.SAND, 1));

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
	public void onRclickChest(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!(e.getClickedBlock().getType().equals(Material.CHEST)
					|| e.getClickedBlock().getType()
							.equals(Material.ENDER_CHEST) || e
					.getClickedBlock().getType().equals(Material.WORKBENCH))) {
				return;
			}
			if (e.getPlayer().hasPermission(Permissions.interact)) {
				return;
			}
			e.setCancelled(true);
			e.getPlayer().sendMessage(
					ChatColor.RED + "Access permitted" + ChatColor.RESET);
		}
	}

	@EventHandler
	public void openBenches(PlayerInteractEvent e) {

		if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

			if (e.getItem().getType().equals(Material.WORKBENCH)
					&& e.getPlayer().hasPermission(Permissions.openVWorkBench)) {
				e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
			} else if (e.getItem().getType().equals(Material.ENDER_CHEST)
					&& e.getPlayer().hasPermission(Permissions.openEChest)) {
				e.getPlayer().openInventory(e.getPlayer().getEnderChest());
			} else if (e.getItem().getType().equals(Material.ENCHANTMENT_TABLE)
					&& e.getPlayer().hasPermission(
							Permissions.openVEnchantingTable)) {
				e.getPlayer().openEnchanting(e.getPlayer().getLocation(), true);

			}
		}
	}

	@EventHandler
	public void spongejump(PlayerMoveEvent ev) {

		if (getBlockUnderFeet(ev.getPlayer(), 0, 1.0D, 0).getType() == Material.SPONGE) {
			if (!ev.getPlayer().isSneaking()) {

				try {
					if (ev.getPlayer().getInventory().getBoots().getItemMeta()
							.getDisplayName().equalsIgnoreCase("Jump")) {

						double dx;
						double dy;
						double dz;
						dy = 2.4D;
						if (getBlockUnderFeet(ev.getPlayer(), 0, 2.0D, 0)
								.getType().equals(Material.IRON_BLOCK)) {
							dx = 2.4D;
						} else if (getBlockUnderFeet(ev.getPlayer(), 0, 2.0D, 0)
								.getType().equals(Material.DIAMOND_BLOCK)) {
							dx = -2.4D;
						} else {
							dx = 0.0D;
						}
						if (getBlockUnderFeet(ev.getPlayer(), 0, 3.0D, 0)
								.getType().equals(Material.IRON_BLOCK)) {
							dz = 2.4D;
						} else if (getBlockUnderFeet(ev.getPlayer(), 0, 3.0D, 0)
								.getType().equals(Material.DIAMOND_BLOCK)) {
							dz = -2.4D;
						} else {
							dz = 0.0D;
						}
						Vector vec = new Vector(dx, dy, dz);
						ev.getPlayer().setVelocity(vec);

					}

				} catch (NullPointerException NPE) {
					//
				}

			}
		}
	}

	@EventHandler
	public void onplayerrBed(PlayerInteractEvent event) {
		if (event.getPlayer().hasPermission(Permissions.modifyBlock)) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
					&& (event.getClickedBlock().getType().equals(Material.BED) || event
							.getClickedBlock().getType()
							.equals(Material.BED_BLOCK))) {

				event.setCancelled(true);
				event.getPlayer().setPlayerTime(42000, true);
				event.getPlayer().setBedSpawnLocation(
						event.getClickedBlock().getLocation());
				event.getPlayer().sendMessage(
						ChatColor.BLUE + "Bedspawn location set!");
				event.setCancelled(false);
				event.getPlayer().resetPlayerTime();
			}
		}

	}

	@EventHandler
	public void bedenter(PlayerBedEnterEvent e) {

		e.setCancelled(false);

	}

	public static Block getBlockUnderFeet(Player p, double minusX,
			double minusY, double minusZ) {
		return new Location(p.getWorld(), p.getLocation().getX() - minusX, p
				.getLocation().getY() - minusY,
				p.getLocation().getZ() - minusZ, p.getLocation().getPitch(), p
						.getLocation().getYaw()).getBlock();

	}

	@EventHandler
	public void vehiclecoll(VehicleDestroyEvent e) {
		e.setCancelled(e.getVehicle().getType().equals(Material.BOAT)
				|| e.getVehicle().getType().equals(Material.MINECART)
				&& (e.getAttacker() == null) ? true : e.isCancelled());
	}

}

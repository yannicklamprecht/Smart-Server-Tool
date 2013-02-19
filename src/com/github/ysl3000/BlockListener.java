package com.github.ysl3000;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.server.v1_4_R1.TileEntityFurnace;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.craftbukkit.v1_4_R1.inventory.CraftInventoryFurnace;
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
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BlockListener implements Listener {

	public BlockListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onbreak(BlockBreakEvent event) {
		event.setCancelled(Permission.hasCreate(event.getPlayer())
				|| ConfigLoader.getNonPermission() ? false : true);
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onbuild(BlockPlaceEvent event) {
		event.setCancelled(!Permission.hasCreate(event.getPlayer())
				&& !ConfigLoader.getNonPermission());
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
		event.setCancelled(ConfigLoader.isBlockburn());
	}

	@EventHandler
	public void onblockig(BlockIgniteEvent event) {
		event.setCancelled(event.getCause().equals(IgniteCause.LAVA) ? ConfigLoader
				.isLavaspread() : event.getCause()
				.equals(IgniteCause.LIGHTNING) ? ConfigLoader
				.isLightning_spread() : event.getCause().equals(
				IgniteCause.SPREAD) ? ConfigLoader.isNormalspread() : false);
	}

	@EventHandler
	public void BlockDrops(BlockBreakEvent event) {
		if (!event.isCancelled()) {
			if (event.getPlayer().getItemInHand().getEnchantments()
					.containsKey(new EnchantmentWrapper(33)))
				return;
			Random rando = new Random();
			if (event.getBlock().getType().equals(Material.DIAMOND_ORE)
					&& (rando.nextInt(ConfigLoader.getDiamondDropChance()) == 1 || ConfigLoader
							.getDiamondDropChance() == 1)
					&& ConfigLoader.isDiamondDrop()) {
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.DIAMOND_PICKAXE));
			} else if (event.getBlock().getType().equals(Material.LEAVES)
					&& ConfigLoader.isAppleShear()
					&& event.getPlayer().getItemInHand().getType()
							.equals(Material.SHEARS)) {
				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(Material.GOLDEN_APPLE, 1));

			} else if (event.getBlock().getTypeId() == 102
					&& (rando.nextInt(ConfigLoader.getGlassPaneDropChance()) == 1 || ConfigLoader
							.getGlassPaneDropChance() == 1)
					&& ConfigLoader.isGlassPaneDrop()) {

				event.getBlock()
						.getWorld()
						.dropItem(event.getBlock().getLocation(),
								new ItemStack(102, 1));

			} else if (event.getBlock().getType().equals(Material.GLASS)
					&& (rando.nextInt(ConfigLoader.getGlassSandDropChance()) == 1 || ConfigLoader
							.getGlassSandDropChance() == 1)
					&& ConfigLoader.isGlassSandDrop()) {
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
	public void leaveDecay(LeavesDecayEvent e) {

		Random random2 = new Random();

		if (random2.nextInt(ConfigLoader.getAppleDropChance()) == 1) {
			if (ConfigLoader.isappleDrop()) {
				e.getBlock()
						.getWorld()
						.dropItem(e.getBlock().getLocation(),
								new ItemStack(Material.GOLDEN_APPLE, 1));
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
			if (Permission.hasCreate(e.getPlayer())
					|| ConfigLoader.getNonPermission()) {
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
					&& Permission.hasVWorbench(e.getPlayer())) {
				e.getPlayer().openWorkbench(e.getPlayer().getLocation(), true);
			} else if (e.getItem().getType().equals(Material.ENDER_CHEST)
					&& Permission.hasVEnderchest(e.getPlayer())) {
				e.getPlayer().openInventory(e.getPlayer().getEnderChest());
			} else if (e.getItem().getType().equals(Material.ENCHANTMENT_TABLE)
					&& Permission.hasVEnchantingTable(e.getPlayer())) {
				e.getPlayer().openEnchanting(e.getPlayer().getLocation(), true);
			} else if (e.getItem().getType().equals(Material.FURNACE)) {

				e.getPlayer().openInventory(
						new CraftInventoryFurnace(new TileEntityFurnace()));

			} else if (e.getItem().getType().equals(Material.ANVIL)) {
				e.getPlayer().sendMessage("Will be added soon");

			} else if (e.getItem().getType().equals(Material.MAGMA_CREAM)) {
				if (e.getItem().getItemMeta().getDisplayName() != null &&(e.getItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("advanced_pack")
						|| e.getItem().getItemMeta().getDisplayName()
								.equalsIgnoreCase("starter_pack"))) {
					if (inventoryIsEmpty(e.getPlayer().getInventory(), 3)) {

						if (e.getItem().getAmount() == 1) {
							e.getPlayer().getInventory()
									.remove(e.getPlayer().getItemInHand());
						} else {
							e.getPlayer()
									.getItemInHand()
									.setAmount(
											e.getPlayer().getItemInHand()
													.getAmount() - 1);
						}

						addStuffForMagmaCream(e.getPlayer(), e.getItem()
								.getItemMeta().getDisplayName());

					} else {
						e.getPlayer()
								.sendMessage(
										ChatColor.RED
												+ "Not enough space in inventory to extract stuff"
												+ ChatColor.RESET);
					}
				}
			}
		}
	}

	private static boolean inventoryIsEmpty(Inventory iv, int minfree) {
		int ivs = 0;
		for (int i = iv.getSize(); i > 0; i--) {
			if (iv.getItem(i) == null) {
				ivs++;
			}
		}
		if (ivs >= minfree) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("deprecation")
	private static void addStuffForMagmaCream(Player p, String pack_data) {
		if (pack_data.equalsIgnoreCase("starter_pack")) {
			ArrayList<Material> list = new ArrayList<Material>();

			list.add(Material.WOOD_AXE);
			list.add(Material.WOOD_HOE);
			list.add(Material.WOOD_PICKAXE);
			list.add(Material.WOOD_SPADE);
			list.add(Material.WOOD_SWORD);

			for (Material mat : list) {
				if (p.getInventory().contains(mat)) {
					addictItems(p, mat);
				} else {
					p.getInventory().addItem(new ItemStack(mat, 1));
				}
			}

		} else if (pack_data.equalsIgnoreCase("advanced_pack")) {
			ArrayList<Material> list = new ArrayList<Material>();

			list.add(Material.WORKBENCH);
			list.add(Material.ENCHANTMENT_TABLE);
			list.add(Material.ENDER_CHEST);
			for (Material mat : list) {
				if (p.getInventory().contains(mat)) {
					addictItems(p, mat);
				} else {
					p.getInventory().addItem(new ItemStack(mat, 1));
				}
			}
		}
		p.updateInventory();
	}

	private static void addictItems(Player p, Material mat) {
		p.getInventory().setItem(
				p.getInventory().first(mat),
				new ItemStack(mat, p.getInventory()
						.getItem(p.getInventory().first(mat)).getAmount() + 1));
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
	public void onplayerrb(PlayerInteractEvent event) {
		if (Permission.hasCreate(event.getPlayer())
				|| ConfigLoader.getNonPermission()) {
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
					&& (event.getClickedBlock().getType().equals(Material.BED) || event
							.getClickedBlock().getType()
							.equals(Material.BED_BLOCK))) {

				if (event.getPlayer().getWorld().getTime() <= 13000
						&& event.getPlayer().getWorld().getTime() >= 0) {

					event.setCancelled(true);
					event.getPlayer().setBedSpawnLocation(
							event.getClickedBlock().getLocation());
					event.getPlayer().sendMessage(
							ChatColor.BLUE + "Bedspawn location set!");
				}
			}
		} else {
			return;
		}

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

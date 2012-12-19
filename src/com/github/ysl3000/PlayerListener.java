package com.github.ysl3000;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.Dispenser;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
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
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.vehicle.VehicleBlockCollisionEvent;
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

		if (Permission.hasInfo(player)) {

			player.sendMessage("Infos of: " + target.getDisplayName());
			player.sendMessage("Foodlevel: " + target.getFoodLevel());
			player.sendMessage("Health: " + target.getHealth());
			player.sendMessage("Ip: " + target.getAddress());
			player.sendMessage("Op-status: " + target.isOp());
			player.sendMessage("Gamemode: " + target.getGameMode());
			player.sendMessage("XP: " + target.getTotalExperience());
			player.sendMessage("XP-Level: " + target.getLevel());

		}

	}

	@EventHandler
	public void onbreak(BlockBreakEvent event) {

		Player player = event.getPlayer();

		if (Permission.hasCreate(player)) {

			BlockDrops(event);

		} else {

			event.setCancelled(ConfigLoader.isBlockbreak()
					|| ConfigLoader.getNonPermission());
			BlockDrops(event);
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onbuild(BlockPlaceEvent event) {

		event.setCancelled(Permission.hasCreate(event.getPlayer()) ? false
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
	public void onplayerdivideWater(EntityChangeBlockEvent e) {
		if ((e.getBlock().getType().equals(Material.WATER) || e.getBlock()
				.getType().equals(Material.LAVA))
				&& (e.getEntity() instanceof Player)) {
			Player p = (Player) e.getEntity();

			if (!Permission.hasCreate(p)) {
				e.setCancelled(true);
				e.getBlock().setType(e.getBlock().getType());
			}

		}

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
		if (Permission.hasCreate(event.getPlayer())
				|| (!ConfigLoader.isInteract())) {
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
	public void onblockburn(BlockBurnEvent event) {

		event.setCancelled(ConfigLoader.isBlockburn());

	}

	@EventHandler
	public void onblockig(BlockIgniteEvent event) {

		if (event.getCause().equals(IgniteCause.LAVA)) {

			event.setCancelled(ConfigLoader.isLavaspread());
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

			if (event.getPlayer().getItemInHand().getEnchantments()
					.containsKey(new EnchantmentWrapper(33)))
				return;

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

				if (ConfigLoader.isAppleShear()) {
					if (event.getPlayer().getItemInHand().getType()
							.equals(Material.SHEARS)) {
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
	public void vehiclecoll(VehicleDestroyEvent e) {
		e.setCancelled(e.getVehicle().getType().equals(Material.BOAT)
				|| e.getVehicle().getType().equals(Material.MINECART)
				&& (e.getAttacker() == null) ? true : e.isCancelled());

	}

	@EventHandler
	public void playerdammage(EntityDamageEvent e) {

		e.setCancelled(e.getEntityType().equals(EntityType.PLAYER) ? HashmapHandler
				.isGod((Player) e.getEntity()) : e.isCancelled());

	}

	@EventHandler
	public void chatHandler(AsyncPlayerChatEvent e) {

		if (Permission.hasAllowChat(e.getPlayer())
				|| ConfigLoader.getNonPermission()) {
			e.setCancelled(e.isCancelled());
		} else {
			e.setCancelled(true);
			e.getPlayer().sendMessage(SmartServerTool.noperms);
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		e.setCancelled((Permission.hasAllowMove(e.getPlayer()) || ConfigLoader
				.getNonPermission()) ? e.isCancelled() : true);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		if (event.getMessage().startsWith("@")) {

			if (event.getMessage().startsWith("@all ")) {

				event.setMessage(event.getMessage().substring(5));
				event.setFormat(ChatColor.AQUA + "[global]" + ChatColor.WHITE
						+ event.getFormat());
				return;
			} else if (event.getMessage().startsWith("@op ")) {

				event.setMessage(event.getMessage().substring(4));
				removeRecipients(event, ChatColor.RED + "[Need-OP]", true);
				event.getPlayer().sendMessage(ChatColor.GRAY + "Request sent!");
			} else {
				try {
					String[] cs = event.getMessage().split(" ");
					Bukkit.getPlayer(
							event.getMessage().substring(1, cs[0].length()))
							.sendMessage(privateMessage(event, cs));
					event.getPlayer().sendMessage(privateMessage(event, cs));
				} catch (Exception e) {
					event.getPlayer().sendMessage(
							ChatColor.RED + "Player not found");
				}
				event.setCancelled(true);
			}
		} else {
			if (HashmapHandler.getChannel(event.getPlayer().getName())
					.equalsIgnoreCase("g")) {
				event.setFormat(ChatColor.AQUA + "[global]" + ChatColor.WHITE
						+ event.getFormat());
			} else {
				removeRecipients(event,
						HashmapHandler.getChannel(event.getPlayer().getName()),
						false);
			}
		}

	}

	public static String privateMessage(AsyncPlayerChatEvent event, String[] cs) {

		return ChatColor.GRAY
				+ "(from "
				+ event.getPlayer().getDisplayName()
				+ ChatColor.GRAY
				+ " to "
				+ Bukkit.getPlayer(
						event.getMessage().substring(1, cs[0].length()))
						.getDisplayName() + ChatColor.GRAY + "): "
				+ ChatColor.RESET
				+ event.getMessage().substring(cs[0].length() + 1);
	}

	@EventHandler
	public void onPlayerCreatureSpawnerChange(PlayerInteractEvent e) {

		if (e.getClickedBlock() == null)
			return;
		if (e.getClickedBlock().getType().equals(Material.MOB_SPAWNER)
				&& e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			if (Permission.hasChangeSpawnerType(e.getPlayer())) {
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

	public static void removeRecipients(AsyncPlayerChatEvent event,
			String channelname, boolean opCall) {

		Player[] recipients = event.getRecipients().toArray(new Player[0]);

		for (int i = 0; i < recipients.length; i++) {

			if (opCall) {

				if (!recipients[i].isOp()) {
					event.getRecipients().remove(recipients[i]);
				}

			} else {
				if (!(HashmapHandler.getChannel(recipients[i].getName())
						.equalsIgnoreCase(HashmapHandler.getChannel(event
								.getPlayer().getName())))) {

					event.getRecipients().remove(recipients[i]);

				}
			}

			event.setFormat(channelname + event.getFormat());

		}

	}

	@EventHandler
	public void leaveDecay(LeavesDecayEvent e) {

		Random random2 = new Random();

		if (random2.nextInt(ConfigLoader.getAppleDropChance()) == 1
				|| ConfigLoader.getAppleDropChance() == 1) {
			if (ConfigLoader.isappleDrop()) {
				e.getBlock()
						.getWorld()
						.dropItem(e.getBlock().getLocation(),
								new ItemStack(Material.GOLDEN_APPLE, 1));
			}

		}

	}

	@EventHandler
	public void colly(VehicleBlockCollisionEvent e) {

		if (e.getBlock().getType().equals(Material.DISPENSER)) {

			e.getVehicle().leaveVehicle();
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
	public void nofood(FoodLevelChangeEvent e) {

		e.setCancelled(e.getEntityType().equals(EntityType.PLAYER) ? HashmapHandler
				.isGod((Player) e.getEntity()) : e.isCancelled());

	}

	@EventHandler
	public void nobucketFill(PlayerBucketFillEvent e) {
		e.setCancelled(!(Permission.hasCreate(e.getPlayer()) || ConfigLoader
				.getNonPermission()));
	}

	@EventHandler
	public void nobucketEmpty(PlayerBucketEmptyEvent e) {

		e.setCancelled(!(Permission.hasCreate(e.getPlayer()) || ConfigLoader
				.getNonPermission()));

	}

	@EventHandler
	public void addItemsToMonster(PlayerInteractEntityEvent e) {

		
		if (e.getPlayer().getItemInHand().getType()
				.equals(Material.DIAMOND_HELMET)) {

			if (e.getRightClicked() instanceof LivingEntity) {
				LivingEntity le = (LivingEntity) e.getRightClicked();
				
				le.getEquipment().setHelmet(
						new ItemStack(e.getPlayer().getItemInHand().getType(),
								e.getPlayer().getItemInHand().getAmount()));
				
				
				le.playEffect(EntityEffect.DEATH);
			}
		}
	}

}
package com.github.ysl3000;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import net.minecraft.server.Block;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

	private SmartServerTool plugin;
	private boolean Bbuild;
	private boolean Blockbreak;
	private boolean Bcreeper;
	private boolean Bender;
	private boolean xpsave;
	private static double x;
	private static double y;
	private static double z;
	private static float pitch;
	private static float yaw;
	private static FileReader fr;
	private static BufferedReader br;
	private static HashMap<Player, Location> LastL = new HashMap<Player, Location>();

	public PlayerListener(SmartServerTool smartServerTool) {

		this.plugin = smartServerTool;

		Bbuild = this.plugin.getConfig().getBoolean("disallowbuild");
		Blockbreak = this.plugin.getConfig().getBoolean("disablebreak");
		Bcreeper = this.plugin.getConfig().getBoolean("Blockcreeper");
		Bender = this.plugin.getConfig().getBoolean("Blockender");
		xpsave = this.plugin.getConfig().getBoolean("xpsave");

	}

	public static HashMap<Player, Location> getlocation() {

		return LastL;

	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {

		Player player = event.getPlayer();

		if (player.getBedSpawnLocation() == null) {

			String world = player.getWorld().getName();

			String all;

			fr = new FileReader((SmartServerTool.getMainDirectory())
					+ "/spawns/" + world + "Spawn.yml");

			br = new BufferedReader(fr);

			all = br.readLine();

			String[] data = all.split(":");

			World wo = Bukkit.getWorld(data[0]);
			x = Double.parseDouble(data[1]);
			y = Double.parseDouble(data[2]);
			z = Double.parseDouble(data[3]);
			pitch = Float.parseFloat(data[4]);
			yaw = Float.parseFloat(data[5]);

			Location lc = new Location(wo, x, y, z, yaw, pitch);

			lc.setWorld(wo);
			lc.setPitch(pitch);
			lc.setYaw(yaw);
			lc.setX(x);
			lc.setY(y);
			lc.setZ(z);

			br.close();
			fr.close();

			player.teleport(lc);

		} else if (player.getBedSpawnLocation() != null) {

			player.teleport(player.getBedSpawnLocation());
		}

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) throws Exception {

		Player player = event.getEntity();

		LastL.put(player, player.getLocation());

		if (xpsave) {
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

			if (event.getBlock().getType().equals(Block.DIAMOND_ORE)) {

				event.getBlock()
						.getWorld()
						.dropItemNaturally(event.getBlock().getLocation(),
								new ItemStack(Material.DIAMOND_AXE));
				event.getBlock()
						.getWorld()
						.dropItemNaturally(event.getBlock().getLocation(),
								new ItemStack(Material.DIAMOND_PICKAXE));
				event.getBlock()
						.getWorld()
						.dropItemNaturally(event.getBlock().getLocation(),
								new ItemStack(Material.DIAMOND_SPADE));
			} else if (event.getBlock().getType().equals(Material.LEAVES)) {

				World world = event.getBlock().getWorld();
				ItemStack bread = new ItemStack(Material.APPLE, 4);

				bread.setAmount(3);
				bread.setType(Material.APPLE);

				world.dropItemNaturally(event.getBlock().getLocation(), bread);
				bread.setAmount(1);
				bread.setType(Material.GOLDEN_APPLE);
				world.dropItemNaturally(event.getBlock().getLocation(), bread);

			}

		} else {

			event.setCancelled(Blockbreak);
		}

	}

	@EventHandler
	public void onbuild(BlockPlaceEvent event) throws Exception {

		Player player = event.getPlayer();

		if (player.hasPermission("sst.build")) {

			event.setCancelled(false);

		} else {
			event.setCancelled(Bbuild);
		}

	}

	@EventHandler
	public void oncreeper(EntityExplodeEvent event) {

		if (!event.getEntityType().equals(EntityType.CREEPER)) {
			return;
		}

		event.setCancelled(Bcreeper);

	}

	@EventHandler
	public void onenderpick(EntityChangeBlockEvent event) {

		if (!event.getEntityType().equals(EntityType.ENDERMAN)) {
			return;
		}
		event.setCancelled(Bender);

	}

	@EventHandler
	public void onplayerdive(PlayerToggleSneakEvent event) {

		Player player = event.getPlayer();

		if (player.getInventory().getHelmet() != null) {

			if (player.getRemainingAir() <= 5) {

				short dur = player.getInventory().getHelmet().getDurability();
				player.setRemainingAir(300);

				short newdur = (short) (dur + 5);
				player.getInventory().getHelmet().setDurability(newdur);
				player.sendMessage(ChatColor.GOLD + "Air recharged!!");

			} else {
				return;
			}
		} else {

			if (player.getRemainingAir() <= 5) {
				player.sendMessage(ChatColor.RED
						+ "you have no helmet to charge your breath");
			} else {
				return;
			}

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
		}else{
			return;
		}

	}

}

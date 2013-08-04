package com.github.ysl3000.Event;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
import com.github.ysl3000.SmartServerTool;

public class SignListener implements Listener {

	public SignListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent e) {

		if (e.getLine(1).equalsIgnoreCase("[free]")) {
			if ((e.getLine(2).isEmpty() || !StringUtils.isNumeric(e.getLine(2)))
					|| (e.getLine(3).isEmpty() || !StringUtils.isNumeric(e
							.getLine(3)))
					|| !(Integer.parseInt(e.getLine(3)) % 9 == 0)) {

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

		e.setLine(0, e.getPlayer().getName());

	}

	@EventHandler
	public void rclickSign(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

			
			if (e.getClickedBlock().getType().equals(Material.SIGN)
					|| e.getClickedBlock().getType().equals(Material.SIGN_POST)) {

				Sign s = (Sign) e.getClickedBlock().getState();

				if (s.getLine(1).startsWith("[") && s.getLine(1).endsWith("]")) {
					e.setCancelled(true);
				}

				if (s.getLine(1).equalsIgnoreCase("[free]")) {
					if (s.getLine(2).isEmpty())
						return;
					int itemtype = Integer.parseInt(s.getLine(2));
					int chestSize = Integer.parseInt(s.getLine(3));

					Inventory iv = Bukkit.createInventory(null, chestSize,
							"Virtuell");

					for (int i = 0; i < iv.getSize(); i++) {
						ItemStack its = new ItemStack(itemtype);
						its.setAmount(64);
						iv.setItem(i, its);
					}

					e.getPlayer().openInventory((Inventory) iv);
				} else if (s.getLine(1).equalsIgnoreCase("[del]")) {
					Inventory iv = Bukkit.createInventory(null, 9, "Disposal");
					e.getPlayer().openInventory(iv);
				} else if (s.getLine(1).equalsIgnoreCase("[join]")) {
					if (e.getPlayer().isSneaking()){
						e.setCancelled(false);
						return;
					}
					this.auslagerung(e.getPlayer(), e.getClickedBlock());
				}
			}
		}

	}

	@EventHandler
	public void onSignDestroy(PlayerInteractEvent e) {

		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)
				&& (e.getClickedBlock().getType().equals(Material.WALL_SIGN) || e
						.getClickedBlock().getType().equals(Material.SIGN_POST))) {

			Sign s = (Sign) e.getClickedBlock().getState();

			if (!s.getLine(0).equalsIgnoreCase(e.getPlayer().getName())) {
				e.setCancelled(true);
			}

		}

	}

	@EventHandler
	public void onBlockSignUnderDestroy(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getRelative(BlockFace.UP).getType()
					.equals(Material.SIGN_POST)) {
				e.setCancelled(isBlock(e.getClickedBlock(), BlockFace.UP) ? true
						: e.isCancelled());
			} else if (e.getClickedBlock().getRelative(BlockFace.SOUTH)
					.getType().equals(Material.WALL_SIGN)) {
				e.setCancelled(isBlock(e.getClickedBlock(), BlockFace.SOUTH) ? true
						: e.isCancelled());
			} else if (e.getClickedBlock().getRelative(BlockFace.NORTH)
					.getType().equals(Material.WALL_SIGN)) {
				e.setCancelled(isBlock(e.getClickedBlock(), BlockFace.NORTH) ? true
						: e.isCancelled());
			} else if (e.getClickedBlock().getRelative(BlockFace.WEST)
					.getType().equals(Material.WALL_SIGN)) {
				e.setCancelled(isBlock(e.getClickedBlock(), BlockFace.WEST) ? true
						: e.isCancelled());
			} else if (e.getClickedBlock().getRelative(BlockFace.EAST)
					.getType().equals(Material.WALL_SIGN)) {
				e.setCancelled(isBlock(e.getClickedBlock(), BlockFace.EAST) ? true
						: e.isCancelled());
			}
		}
	}

	private boolean isBlock(Block bl, BlockFace bf) {
		boolean b = false;
		Block attachedBlock = bl.getRelative(bf).getRelative(
				((org.bukkit.material.Sign) bl.getRelative(bf).getState()
						.getData()).getAttachedFace());
		if (bl.getLocation().equals(attachedBlock.getLocation())) {
			b = true;
		}
		return b;
	}

	public void auslagerung(Player p, Block clicked) {
		Block b = p.getWorld().getBlockAt(
				new Location(p.getWorld(), clicked.getLocation().getX(),
						clicked.getLocation().getY() + 1, clicked.getLocation()
								.getZ()));

		if (b.getType().equals(Material.SKULL)) {
			b.setType(Material.SKULL);
			Skull sk = (Skull) b.getState();
			sk.setSkullType(SkullType.PLAYER);
			sk.setOwner(p.getName());
			sk.update();
		} else {
			p.sendMessage("You Have to place a Skullhead first");
		}
	}
}

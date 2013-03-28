package com.github.ysl3000.Event;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
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
		}

		e.setLine(0, e.getPlayer().getName());

	}

	@EventHandler
	public void rclickSign(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getClickedBlock().getType().equals(Material.SIGN)
					|| e.getClickedBlock().getType().equals(Material.SIGN_POST)) {

				Sign s = (Sign) e.getClickedBlock().getState();

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

					e.setCancelled(true);
				}
			}
		}

	}

	@EventHandler
	public void onSignDestroy(PlayerInteractEvent e) {

		if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)
				&& (e.getClickedBlock().getType().equals(Material.SIGN) || e
						.getClickedBlock().getType().equals(Material.SIGN_POST))) {

			Sign s = (Sign) e.getClickedBlock().getState();

			if (!s.getLine(0).equalsIgnoreCase(e.getPlayer().getName())) {
				e.setCancelled(true);
			}

		}

	}
}

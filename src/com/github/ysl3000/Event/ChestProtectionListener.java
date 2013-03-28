package com.github.ysl3000.Event;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.github.ysl3000.SmartServerTool;

public class ChestProtectionListener implements Listener {

	public ChestProtectionListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onChestCreation(BlockPlaceEvent e) {
		if (e.getBlock().getType().equals(Material.CHEST)) {

		}
	}

	@EventHandler
	public void onChestOpen(PlayerInteractEvent e) {

		if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& e.getClickedBlock().getType().equals(Material.CHEST)) {

			Chest ch = (Chest) e.getClickedBlock().getState();
			if (!(ch.getInventory().getName()
					.equalsIgnoreCase(e.getPlayer().getName())
					|| ch.getInventory().getName().equalsIgnoreCase("public") || SmartServerTool
					.getPermission().hasOpenanyChest(e.getPlayer()))) {
				e.setCancelled(true);
				e.getPlayer().sendMessage(
						"This chest is protected to"
								+ ch.getInventory().getName());
			}
		}
	}
}

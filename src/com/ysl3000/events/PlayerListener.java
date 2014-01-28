package com.ysl3000.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.plugin.SmartServerTool;
import com.ysl3000.utils.ConfigFactorizer;
import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.SmartPlayer;

public class PlayerListener implements Listener {
	private JavaPlugin plugin;
	public PlayerListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin=plugin;
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) throws Exception {
		event.getPlayer().teleport(
				event.getPlayer().getBedSpawnLocation() == null ? event
						.getPlayer().getWorld().getSpawnLocation() : event
						.getPlayer().getBedSpawnLocation());

	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		Entity etarget = event.getRightClicked();
		if (!(etarget instanceof Player)) {
			return;
		}
		Player target = (Player) etarget;

		if (player.hasPermission(Permissions.playerInfo)) {
			player.sendMessage("Infos of: " + target.getDisplayName());
			player.sendMessage("Foodlevel: " + target.getFoodLevel());
			player.sendMessage("Health: " + "Not available");
			player.sendMessage("Ip: " + target.getAddress());
			player.sendMessage("Op-status: " + target.isOp());
			player.sendMessage("Gamemode: " + target.getGameMode());
			player.sendMessage("XP: " + target.getTotalExperience());
			player.sendMessage("XP-Level: " + target.getLevel());
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
	public void playerteleport(PlayerTeleportEvent event) {

		if (!HashMapController.getHashMapControler()
				.getSmartPLayers().containsKey(event.getPlayer().getUniqueId())) {
			HashMapController.getHashMapControler()
					.getSmartPLayers()
					.put(event.getPlayer().getUniqueId(),
							new SmartPlayer(event.getPlayer()));
		}
		HashMapController.getHashMapControler().getSmartPLayers()
				.get(event.getPlayer().getUniqueId())
				.setLastLocation(event.getFrom());
		HashMapController.getHashMapControler().getSmartPLayers()
				.get(event.getPlayer().getUniqueId())
				.setCurrentLocation(event.getTo());
	}

	@EventHandler
	public void chatHandler(AsyncPlayerChatEvent e) {
		if (!e.getPlayer().hasPermission(Permissions.chat)
				&& !ConfigFactorizer.createAndReturn(this.plugin)
						.getNonPermission()) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("NoPermissions");
		}
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		if (!HashMapController.getHashMapControler()
				.getSmartPLayers().containsKey(e.getPlayer().getUniqueId())) {
			HashMapController.getHashMapControler()
					.getSmartPLayers()
					.put(e.getPlayer().getUniqueId(),
							new SmartPlayer(e.getPlayer()));
		}

		e.setCancelled((!(e.getPlayer().hasPermission(Permissions.move) || ConfigFactorizer
				.createAndReturn(this.plugin).getNonPermission()) && !HashMapController.getHashMapControler().getSmartPLayers()
				.get(e.getPlayer().getUniqueId()).isFrozen()));

	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {

		if (event.getMessage().startsWith("@")) {

			if (event.getMessage().startsWith("@op ")) {

				event.setMessage(event.getMessage().substring(4));
				removeRecipients(event, ChatColor.RED + "[Need-OP]"
						+ ChatColor.RESET, true);
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
		}

	}

	public String privateMessage(AsyncPlayerChatEvent event, String[] cs) {

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

	public void removeRecipients(AsyncPlayerChatEvent event,
			String channelname, boolean opCall) {

		Player[] recipients = event.getRecipients().toArray(new Player[0]);
		event.setFormat(channelname + event.getFormat());

		for (int i = 0; i < recipients.length; i++) {

			if (opCall) {

				if (!recipients[i].isOp()) {
					event.getRecipients().remove(recipients[i]);
				}
			}
		}
	}

	@EventHandler
	public void nobucketFill(PlayerBucketFillEvent e) {
		e.setCancelled(!(e.getPlayer().hasPermission(Permissions.interact) || ConfigFactorizer
				.createAndReturn(this.plugin).getNonPermission()));
	}

	@EventHandler
	public void nobucketEmpty(PlayerBucketEmptyEvent e) {
		e.setCancelled(!(e.getPlayer().hasPermission(Permissions.interact) || ConfigFactorizer
				.createAndReturn(this.plugin).getNonPermission()));
	}

}
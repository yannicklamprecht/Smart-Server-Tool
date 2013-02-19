package com.github.ysl3000;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
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
		HashmapHandler.setLastLocation(event.getPlayer(), event.getFrom());
		HashmapHandler.setCurrentLocation(event.getPlayer(), event.getTo());
	}

	@EventHandler
	public void chatHandler(AsyncPlayerChatEvent e) {
		if (!Permission.hasAllowChat(e.getPlayer())
				&& !ConfigLoader.getNonPermission()) {
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

	public static void removeRecipients(AsyncPlayerChatEvent event,
			String channelname, boolean opCall) {

		Player[] recipients = event.getRecipients().toArray(new Player[0]);
		event.setFormat(channelname + event.getFormat());

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

		}

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
				.equals(Material.DIAMOND_HELMET)
				&& e.getRightClicked() instanceof LivingEntity) {
			try {
				LivingEntity le = (LivingEntity) e.getRightClicked();

				le.getEquipment().setHelmet(
						new ItemStack(e.getPlayer().getItemInHand().getType(),
								e.getPlayer().getItemInHand().getAmount()));
			} catch (Exception ex) {
				e.getPlayer().sendMessage(
						ChatColor.RED + "Need to update Bukkit");
			}
		}
	}
	
	
	
}
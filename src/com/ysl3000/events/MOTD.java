package com.ysl3000.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.ysl3000.permissions.Permissions;
import com.ysl3000.plugin.SmartServerTool;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SmartController;
import com.ysl3000.utils.SmartPlayer;
import com.ysl3000.utils.Utility;

public class MOTD implements Listener {

	private String coremessage;
	private String joinmessage;
	private String privateJoinmessage;
	private String leftmessage;

	public MOTD(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (SmartServerTool.getConfigLoader().getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if (event.getPlayer().getName().equalsIgnoreCase("Player")
				&& SmartServerTool.getConfigLoader().isBloggingPlayerJoin()) {

			event.setResult(Result.KICK_OTHER);
			event.setKickMessage("Player you are not allowed to join, because of stupidness");
		}

		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(SmartServerTool.getConfigLoader()
					.getWhitelistmessage());

			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 14));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(SmartServerTool.getConfigLoader()
					.getBanmessage());
			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 11));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (event.getPlayer().hasPermission(Permissions.joinFull)) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(SmartServerTool.getConfigLoader()
						.getFullmessage());
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (event.getPlayer().hasPermission(Permissions.joinService)) {

				event.setResult(Result.ALLOWED);

			} else {

				event.setKickMessage(ChatColor.DARK_RED
						+ SmartServerTool.getConfigLoader()
								.getMaintenanceMessage());
			}
		}

	}

	@EventHandler
	public void serverlistcheck(ServerListPingEvent event) {

		if (SmartServerTool.getConfigLoader().getMaintenance()) {

			event.setMaxPlayers(-2);
			event.setMotd(ChatColor.DARK_RED
					+ SmartServerTool.getConfigLoader().getMaintenanceMessage());

		} else {
			event.setMotd(ChatColor.GOLD + event.getMotd());
		}
	}

	private void message_trying_to_join(String name, String type) {
		Bukkit.broadcast(name + "{" + type + "}" + " trying to join",
				"sst.admin");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		SmartController.getSmartControler().getHashmaps().getSmartPLayers()
		.put(event.getPlayer(), new SmartPlayer(event.getPlayer()));
		message(event);
		player.setSleepingIgnored(SmartServerTool.getConfigLoader()
				.isSleepingIgnored());
	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {
		SmartController.getSmartControler().getHashmaps().getSmartPLayers()
				.remove(event.getPlayer());
		message(event);
	}

	private String name(Player player) {

		String userDisName = null;

		userDisName = player.getName();

		return userDisName;
	}

	private void message(Event event) {

		if (event instanceof PlayerJoinEvent) {

			PlayerJoinEvent eventPJE = (PlayerJoinEvent) event;
			if (SmartServerTool.getConfigLoader().isMessaging()) {

				Player playerPJE = eventPJE.getPlayer();
				if (SmartServerTool.getConfigLoader().getRandomColor()) {
					Prefix.getPrefixer().Pfix(playerPJE);
				}

				if (Runtime.getRuntime().availableProcessors() == 1) {

					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " core");
				} else {
					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " cores");
				}

				joinmessage = SmartServerTool.getConfigLoader()
						.getJoinmessage();
				privateJoinmessage = SmartServerTool.getConfigLoader()
						.getPrivatJoinMessage();

				joinmessage = joinmessage.replace("user%", "" + ChatColor.GOLD
						+ name(playerPJE) + ChatColor.WHITE);
				joinmessage = joinmessage.replace("server%", ""
						+ ChatColor.GREEN + Bukkit.getServerName()
						+ ChatColor.WHITE);
				joinmessage = joinmessage.replace("core%", "" + coremessage);

				joinmessage = joinmessage.replace(
						"time%",
						ChatColor.GOLD
								+ ""
								+ Utility.getTime(
										System.currentTimeMillis(),
										SmartServerTool.getConfigLoader()
												.getTimeFormat()
												.replace("%dp", ":"))

								+ ChatColor.WHITE);
				joinmessage = joinmessage.replace("n%", "\n");
				joinmessage = joinmessage.replace("v%", ChatColor.GREEN
						+ Bukkit.getVersion().substring(11, 16)
						+ ChatColor.WHITE);
				joinmessage = joinmessage.replace("b%",
						Bukkit.getBukkitVersion());

				privateJoinmessage = privateJoinmessage.replace("online%",
						ChatColor.GRAY + "Online ("
								+ Bukkit.getServer().getOnlinePlayers().length
								+ "/" + Bukkit.getMaxPlayers() + "): "
								+ Utility.listPlayers());
				privateJoinmessage = privateJoinmessage.replace("n%", "\n");

				if (SmartServerTool.getConfigLoader().getMaintenance()) {

					for (Player p : Bukkit.getOnlinePlayers()) {

						p.sendMessage(SmartServerTool.getConfigLoader()
								.getMaintenanceMessage());

					}

					eventPJE.setJoinMessage("");
				} else {

					if (!playerPJE.hasPlayedBefore()) {

						for (Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(joinmessage.concat(" "
									+ SmartServerTool.getConfigLoader()
											.getFirstJoinMessage()));
						}
						eventPJE.setJoinMessage("");
						ItemStack starterpack = new ItemStack(
								Material.MAGMA_CREAM, 1);
						ItemMeta itemM = starterpack.getItemMeta();
						itemM.setDisplayName("starter_pack");
						starterpack.setItemMeta(itemM);
						playerPJE.getInventory().addItem(starterpack);

						if (playerPJE.isOp()) {
							ItemStack advancedpack = new ItemStack(
									Material.MAGMA_CREAM, 1);
							ItemMeta advanced_itemM = starterpack.getItemMeta();
							advanced_itemM.setDisplayName("advanced_pack");
							starterpack.setItemMeta(advanced_itemM);
							playerPJE.getInventory().addItem(advancedpack);

						}

					} else {

						for (Player p : Bukkit.getOnlinePlayers()) {

							p.sendMessage(joinmessage);
						}
						eventPJE.setJoinMessage("");

					}
				}
				eventPJE.getPlayer().sendMessage(privateJoinmessage);
			} else {

				if (!eventPJE.getPlayer().hasPlayedBefore()) {
					eventPJE.getPlayer().teleport(
							eventPJE.getPlayer().getWorld().getSpawnLocation());
				} else {
					return;
				}
			}

		} else if (event instanceof PlayerQuitEvent) {

			PlayerQuitEvent eventPQE = (PlayerQuitEvent) event;

			if (SmartServerTool.getConfigLoader().isMessaging()) {
				leftmessage = null;
				leftmessage = SmartServerTool.getConfigLoader()
						.getLeftmessage();

				Player playerPQE = eventPQE.getPlayer();
				leftmessage = leftmessage.replace("user%", ChatColor.GOLD
						+ name(playerPQE) + ChatColor.WHITE);
				leftmessage = leftmessage.replace("server%", ChatColor.GREEN
						+ Bukkit.getServerName() + ChatColor.WHITE);

				eventPQE.setQuitMessage(leftmessage);
			}

		}

	}

}

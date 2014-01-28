package com.ysl3000.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.plugin.SmartServerTool;
import com.ysl3000.utils.ConfigFactorizer;
import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SmartPlayer;
import com.ysl3000.utils.Utility;

public class MOTD implements Listener {

	private JavaPlugin plugin;
	
	public MOTD(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin=plugin;
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (ConfigFactorizer.createAndReturn(this.plugin).getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(ConfigFactorizer.createAndReturn(this.plugin)
					.getWhitelistmessage());

			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 14));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(ConfigFactorizer.createAndReturn(this.plugin)
					.getBanmessage());
			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 11));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (event.getPlayer().hasPermission(Permissions.joinFull)) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(ConfigFactorizer.createAndReturn(this.plugin)
						.getFullmessage());
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (event.getPlayer().hasPermission(Permissions.joinService)) {

				event.setResult(Result.ALLOWED);

			} else {

				event.setKickMessage(ChatColor.DARK_RED
						+ ConfigFactorizer.createAndReturn(this.plugin)
								.getMaintenanceMessage());
			}
		}

	}

	@EventHandler
	public void serverlistcheck(ServerListPingEvent event) {

		if (ConfigFactorizer.createAndReturn(this.plugin).getMaintenance()) {

			event.setMaxPlayers(-2);
			event.setMotd(ChatColor.DARK_RED
					+ ConfigFactorizer.createAndReturn(this.plugin).getMaintenanceMessage());

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
		HashMapController.getHashMapControler().getSmartPLayers()
		.put(event.getPlayer().getUniqueId(), new SmartPlayer(event.getPlayer()));
		message(event);
		player.setSleepingIgnored(ConfigFactorizer.createAndReturn(this.plugin)
				.isSleepingIgnored());
		Prefix.getPrefixer().Pfix(player);
	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {
		HashMapController.getHashMapControler().getSmartPLayers()
				.remove(event.getPlayer().getUniqueId());
		message(event);
	}

	private String name(Player player) {

		String userDisName = null;

		userDisName = player.getName();

		return userDisName;
	}

	private void message(Event event) {

		if (event instanceof PlayerJoinEvent) {

			String coremessage;
			String privateJoinmessage;
			String joinmessage;
			PlayerJoinEvent eventPJE = (PlayerJoinEvent) event;
			if (ConfigFactorizer.createAndReturn(this.plugin).isMessaging()) {

				Player playerPJE = eventPJE.getPlayer();
				if (ConfigFactorizer.createAndReturn(this.plugin).getRandomColor()) {
					Prefix.getPrefixer().Pfix(playerPJE);
				}

				if (Runtime.getRuntime().availableProcessors() == 1) {

					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " core");
				} else {
					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " cores");
				}

				joinmessage = ConfigFactorizer.createAndReturn(this.plugin)
						.getJoinmessage();
				privateJoinmessage = ConfigFactorizer.createAndReturn(this.plugin)
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
										ConfigFactorizer.createAndReturn(this.plugin)
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

				if (ConfigFactorizer.createAndReturn(this.plugin).getMaintenance()) {

					for (Player p : Bukkit.getOnlinePlayers()) {

						p.sendMessage(ConfigFactorizer.createAndReturn(this.plugin)
								.getMaintenanceMessage());

					}

					eventPJE.setJoinMessage("");
				} else {

					if (!playerPJE.hasPlayedBefore()) {

						for (Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(joinmessage.concat(" "
									+ ConfigFactorizer.createAndReturn(this.plugin)
											.getFirstJoinMessage()));
						}
						eventPJE.setJoinMessage("");

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
			String leftmessage;

			if (ConfigFactorizer.createAndReturn(this.plugin).isMessaging()) {
				leftmessage = ConfigFactorizer.createAndReturn(this.plugin)
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

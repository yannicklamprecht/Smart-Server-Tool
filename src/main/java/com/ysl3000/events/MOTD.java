package com.ysl3000.events;

import com.ysl3000.plugin.SmartPlayers;
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
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.SmartPlayer;
import com.ysl3000.utils.Utility;

public class MOTD implements Listener {

	private JavaPlugin plugin;
	private Utility utility;
	private SmartPlayers smartPlayers;
	
	public MOTD(SmartServerTool plugin, Utility utility, SmartPlayers smartPlayers) {
        this.utility = utility;
		this.plugin=plugin;
		this.smartPlayers = smartPlayers;
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (ConfigFactorizer.createAndReturn(this.plugin).getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(ConfigFactorizer.createAndReturn(this.plugin)
					.getWhitelistmessage());

			messageTryingToJoin(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 14));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(ConfigFactorizer.createAndReturn(this.plugin)
					.getBanmessage());
			messageTryingToJoin(event.getPlayer().getDisplayName(), event
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

	private void messageTryingToJoin(String name, String type) {
		Bukkit.broadcast(name + "{" + type + "}" + " trying to join",
				"sst.admin");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();


		// todo refactor build message elsewhere

		String coremessage;
		String privateJoinmessage;
		String joinmessage;

		if (ConfigFactorizer.createAndReturn(this.plugin).isMessaging()) {

			if (ConfigFactorizer.createAndReturn(this.plugin).getRandomColor()) {
				Prefix.getPrefixer().Pfix(player);
			}

			if (Runtime.getRuntime().availableProcessors() == 1) {

				coremessage = Runtime.getRuntime()
						.availableProcessors() + " core";
			} else {
				coremessage = Runtime.getRuntime()
						.availableProcessors() + " cores";
			}

			joinmessage = ConfigFactorizer.createAndReturn(this.plugin)
					.getJoinmessage();
			privateJoinmessage = ConfigFactorizer.createAndReturn(this.plugin)
					.getPrivatJoinMessage();

			joinmessage = joinmessage.replace("user%", "" + ChatColor.GOLD
					+ name(player) + ChatColor.WHITE);
			joinmessage = joinmessage.replace("server%", ""
					+ ChatColor.GREEN + Bukkit.getServerName()
					+ ChatColor.WHITE);
			joinmessage = joinmessage.replace("core%", "" + coremessage);

			joinmessage = joinmessage.replace(
					"time%",
					ChatColor.GOLD
							+ ""
							+ utility.getTime(
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
							+ Bukkit.getServer().getOnlinePlayers().size()
							+ "/" + Bukkit.getMaxPlayers() + "): "
							+ utility.listPlayers());
			privateJoinmessage = privateJoinmessage.replace("n%", "\n");

			if (ConfigFactorizer.createAndReturn(this.plugin).getMaintenance()) {

				for (Player p : Bukkit.getOnlinePlayers()) {

					p.sendMessage(ConfigFactorizer.createAndReturn(this.plugin)
							.getMaintenanceMessage());

				}

				event.setJoinMessage("");
			} else {

				if (!player.hasPlayedBefore()) {

					for (Player p : Bukkit.getOnlinePlayers()) {
						p.sendMessage(joinmessage.concat(" "
								+ ConfigFactorizer.createAndReturn(this.plugin)
								.getFirstJoinMessage()));
					}
					event.setJoinMessage("");

				} else {

					for (Player p : Bukkit.getOnlinePlayers()) {

						p.sendMessage(joinmessage);
					}
					event.setJoinMessage("");

				}
			}
			player.sendMessage(privateJoinmessage);
		} else {

			if (!player.hasPlayedBefore()) {
				player.teleport(
						player.getWorld().getSpawnLocation());
			}
		}


		player.setSleepingIgnored(ConfigFactorizer.createAndReturn(this.plugin)
				.isSleepingIgnored());
		Prefix.getPrefixer().Pfix(player);
	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {
		String leftmessage;

		if (ConfigFactorizer.createAndReturn(this.plugin).isMessaging()) {
			leftmessage = ConfigFactorizer.createAndReturn(this.plugin)
					.getLeftmessage();

			Player playerPQE = event.getPlayer();
			leftmessage = leftmessage.replace("user%", ChatColor.GOLD
					+ name(playerPQE) + ChatColor.WHITE);
			leftmessage = leftmessage.replace("server%", ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.WHITE);

			event.setQuitMessage(leftmessage);
		}
	}

	private String name(Player player) {
		return player.getName();
	}

}

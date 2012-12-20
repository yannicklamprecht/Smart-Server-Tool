package com.github.ysl3000;

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

public class MOTD implements Listener {

	private static String coremessage;
	private static String joinmessage;
	private static String privateJoinmessage;
	private static String leftmessage;

	public MOTD(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (ConfigLoader.getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if (event.getPlayer().getName().equalsIgnoreCase("Player")
				&& ConfigLoader.isBloggingPlayerJoin()) {

			event.setResult(Result.KICK_OTHER);
			event.setKickMessage("Player you are not allowed to join, because of stupidness");
		}

		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(ConfigLoader.getWhitelistmessage());
			
			Bukkit.broadcast(event.getPlayer().getDisplayName()+" trying to join", "sst.admin");
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(ConfigLoader.getBanmessage());
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (Permission.hasJoinFull(event.getPlayer())) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(ConfigLoader.getFullmessage());
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (Permission.hasJoinService(event.getPlayer())) {

				event.setResult(Result.ALLOWED);

			} else {

				event.setKickMessage(ConfigLoader.getMaintenanceMessage());
			}
		}

	}

	@EventHandler
	public void serverlistcheck(ServerListPingEvent event) {

		if (ConfigLoader.getMaintenance()) {

			event.setMaxPlayers(0);
			event.setMotd(ConfigLoader.getMaintenanceMessage());

		}else{
			event.setMotd(ChatColor.GREEN+"[SST]"+ChatColor.GOLD+event.getMotd());
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		HashmapHandler.setIsMOD(player, false);
		HashmapHandler.setGod(player, false);
		HashmapHandler.setFlyStatus(player, false);
		HashmapHandler.setHiddenStatus(player, false);
		HashmapHandler.setChannel(player.getName(), "g");

		HideP.runHide();
		message(event);
		player.setSleepingIgnored(ConfigLoader.isSleepingIgnored());

		if (Permission.hasAutoFly(event.getPlayer())) {
			event.getPlayer().setAllowFlight(true);
			event.getPlayer().setFlying(true);
		}

	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {
		message(event);
	}

	public static String name(Player player) {

		String userDisName = null;

		userDisName = player.getName();

		return userDisName;
	}

	public static void message(Event event) {

		if (event instanceof PlayerJoinEvent) {

			PlayerJoinEvent eventPJE = (PlayerJoinEvent) event;
			if (ConfigLoader.isMessaging()) {

				

				Player playerPJE = eventPJE.getPlayer();
				if (ConfigLoader.getRandomColor()) {
					Prefix.Pfix(playerPJE);
				}

				

				if (Runtime.getRuntime().availableProcessors() == 1) {

					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " core");
				} else {
					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " cores");
				}

				joinmessage = ConfigLoader.getJoinmessage();
				privateJoinmessage = ConfigLoader.getPrivatJoinMessage();

				joinmessage = joinmessage.replace("user%", "" + ChatColor.GOLD
						+ name(playerPJE) + ChatColor.WHITE);
				joinmessage = joinmessage.replace("server%", ""
						+ ChatColor.GREEN + Bukkit.getServerName()
						+ ChatColor.WHITE);
				joinmessage = joinmessage.replace("core%", "" + coremessage);

				joinmessage = joinmessage.replace("time%", ChatColor.GOLD + ""
						+ DateTime.getRealTime(ConfigLoader.getTimeFormat().replace("%dp", ":"), System.currentTimeMillis())
						+  ChatColor.WHITE);
				joinmessage = joinmessage.replace("n%", "\n");

				privateJoinmessage = privateJoinmessage.replace("online%",
						ChatColor.GRAY + "Online ("
								+ Bukkit.getServer().getOnlinePlayers().length
								+ "/" + Bukkit.getMaxPlayers() + "): "
								+ listPlayers());
				privateJoinmessage = privateJoinmessage.replace("n%", "\n");

				if (ConfigLoader.getMaintenance()) {

					for (Player p : Bukkit.getOnlinePlayers()) {

						p.sendMessage(ConfigLoader.getMaintenanceMessage());

					}

					eventPJE.setJoinMessage("");
				} else {

					if (!playerPJE.hasPlayedBefore()) {

						for (Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(joinmessage.concat(" "
									+ ConfigLoader.getFirstJoinMessage()));
						}
						eventPJE.setJoinMessage("");

						Questioner.ask(playerPJE);

						try {
							SpawnArea.tospawn(playerPJE);
						} catch (Exception e) {

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

					try {
						SpawnArea.tospawn(eventPJE.getPlayer());
					} catch (Exception e) {

					}
				} else {
					return;
				}
			}

		} else if (event instanceof PlayerQuitEvent) {

			PlayerQuitEvent eventPQE = (PlayerQuitEvent) event;

			if (ConfigLoader.isMessaging()) {
				leftmessage = null;
				leftmessage = ConfigLoader.getLeftmessage();

				Player playerPQE = eventPQE.getPlayer();
				leftmessage = leftmessage.replace("user%", ChatColor.GOLD
						+ name(playerPQE) + ChatColor.WHITE);
				leftmessage = leftmessage.replace("server%", ChatColor.GREEN
						+ Bukkit.getServerName() + ChatColor.WHITE);

				eventPQE.setQuitMessage(leftmessage);
			}

			if (HashmapHandler.getIsMod(eventPQE.getPlayer()) == true) {

				Top.doneMe(eventPQE.getPlayer());

			} 
		}

	}

	public static String listPlayers() {

		Player ar[] = Bukkit.getOnlinePlayers();
		String liste = "";
		for (Player p : Bukkit.getOnlinePlayers()) {

			if (p.equals(ar[Bukkit.getOnlinePlayers().length - 1])) {

				liste += p.getDisplayName();
			} else {

				liste += p.getDisplayName() + ", ";
			}
		}

		return liste;
	}

}

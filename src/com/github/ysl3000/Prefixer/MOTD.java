package com.github.ysl3000.Prefixer;

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

import com.github.ysl3000.SmartServerTool;
import com.ysl3000.cmdexe.Questioner;
import com.ysl3000.permissions.Permissions;

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

		if (SmartServerTool.getCFGL().getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if (event.getPlayer().getName().equalsIgnoreCase("Player")
				&& SmartServerTool.getCFGL().isBloggingPlayerJoin()) {

			event.setResult(Result.KICK_OTHER);
			event.setKickMessage("Player you are not allowed to join, because of stupidness");
		}

		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(SmartServerTool.getCFGL().getWhitelistmessage());

			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 14));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(SmartServerTool.getCFGL().getBanmessage());
			message_trying_to_join(event.getPlayer().getDisplayName(), event
					.getResult().name().substring(5, 11));
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (event.getPlayer().hasPermission(Permissions.joinFull)) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(SmartServerTool.getCFGL().getFullmessage());
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (event.getPlayer().hasPermission(Permissions.joinService)) {

				event.setResult(Result.ALLOWED);

			} else {

				event.setKickMessage(ChatColor.DARK_RED+SmartServerTool.getCFGL().getMaintenanceMessage());
			}
		}

	}

	@EventHandler
	public void serverlistcheck(ServerListPingEvent event) {

		if (SmartServerTool.getCFGL().getMaintenance()) {

			event.setMaxPlayers(-2);
			event.setMotd(ChatColor.DARK_RED+SmartServerTool.getCFGL().getMaintenanceMessage());

		} else {
			event.setMotd(ChatColor.GOLD + event.getMotd());
		}
	}

	public static void message_trying_to_join(String name, String type) {
		Bukkit.broadcast(name + "{" + type + "}" + " trying to join",
				"sst.admin");
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		SmartServerTool.getHSP().setFlyStatus(player, false);
		SmartServerTool.getHSP().setChannel(player.getName(), "g");
		message(event);
		player.setSleepingIgnored(SmartServerTool.getCFGL().isSleepingIgnored());
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
			if (SmartServerTool.getCFGL().isMessaging()) {

				Player playerPJE = eventPJE.getPlayer();
				if (SmartServerTool.getCFGL().getRandomColor()) {
					Prefix.Pfix(playerPJE);
				}

				if (Runtime.getRuntime().availableProcessors() == 1) {

					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " core");
				} else {
					coremessage = new String(Runtime.getRuntime()
							.availableProcessors() + " cores");
				}

				joinmessage = SmartServerTool.getCFGL().getJoinmessage();
				privateJoinmessage = SmartServerTool.getCFGL().getPrivatJoinMessage();

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
								+ SmartServerTool.getDateTime().getRealTime(SmartServerTool.getCFGL()
										.getTimeFormat().replace("%dp", ":"),
										System.currentTimeMillis())
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
								+ listPlayers());
				privateJoinmessage = privateJoinmessage.replace("n%", "\n");

				if (SmartServerTool.getCFGL().getMaintenance()) {

					for (Player p : Bukkit.getOnlinePlayers()) {

						p.sendMessage(SmartServerTool.getCFGL().getMaintenanceMessage());

					}

					eventPJE.setJoinMessage("");
				} else {

					if (!playerPJE.hasPlayedBefore()) {

						for (Player p : Bukkit.getOnlinePlayers()) {
							p.sendMessage(joinmessage.concat(" "
									+ SmartServerTool.getCFGL().getFirstJoinMessage()));
						}
						eventPJE.setJoinMessage("");

						Questioner.ask(playerPJE);
						eventPJE.getPlayer().teleport(eventPJE.getPlayer().getWorld().getSpawnLocation());

						
						ItemStack starterpack = new ItemStack(Material.MAGMA_CREAM, 1);
						ItemMeta itemM = starterpack.getItemMeta();
						itemM.setDisplayName("starter_pack");
						starterpack.setItemMeta(itemM);
						playerPJE.getInventory().addItem(starterpack);
						
						
						if(playerPJE.isOp()){
							ItemStack advancedpack = new ItemStack(Material.MAGMA_CREAM, 1);
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
					eventPJE.getPlayer().teleport(eventPJE.getPlayer().getWorld().getSpawnLocation());
				} else {
					return;
				}
			}

		} else if (event instanceof PlayerQuitEvent) {

			PlayerQuitEvent eventPQE = (PlayerQuitEvent) event;

			if (SmartServerTool.getCFGL().isMessaging()) {
				leftmessage = null;
				leftmessage = SmartServerTool.getCFGL().getLeftmessage();

				Player playerPQE = eventPQE.getPlayer();
				leftmessage = leftmessage.replace("user%", ChatColor.GOLD
						+ name(playerPQE) + ChatColor.WHITE);
				leftmessage = leftmessage.replace("server%", ChatColor.GREEN
						+ Bukkit.getServerName() + ChatColor.WHITE);

				eventPQE.setQuitMessage(leftmessage);
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

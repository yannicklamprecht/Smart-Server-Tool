package com.github.ysl3000;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {

	private String coremessage;
	private String joinmessage;
	private String timemessage;
	private String leftmessage;

	private static HashMap<Player, Boolean> isMod;

	public MOTD(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (ConfigLoader.getMaintenance()) {

			event.setResult(Result.KICK_OTHER);

		}
		if(event.getPlayer().getName().equalsIgnoreCase("Player")){
			
			event.setResult(Result.KICK_OTHER);
			event.setKickMessage("Player you are not allowed to join, because of stupidness");
		}

		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(ConfigLoader.getWhitelistmessage());
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(ConfigLoader.getBanmessage());
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (event.getPlayer().hasPermission("sst.joinfull")) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(ConfigLoader.getFullmessage());
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (event.getPlayer().hasPermission("sst.joinservice")) {

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

		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		getIsMod().put(player, false);

		if (ConfigLoader.isMessaging()) {

			long second = (System.currentTimeMillis() / 1000);
			long minutes = (second / 60);
			long hour = (minutes / 60);
			long resthour = (hour % 24);
			long restminutes = (minutes % 60);

			Prefix.Pfix(player);
			
			if(player.hasPermission("sst.autofly")){
				player.setAllowFlight(true);
				player.setFlying(true);
			}

			if (Runtime.getRuntime().availableProcessors() == 1) {

				coremessage = new String(Runtime.getRuntime()
						.availableProcessors() + " core");
			} else {
				coremessage = new String(Runtime.getRuntime()
						.availableProcessors() + " cores");
			}

			joinmessage = ConfigLoader.getJoinmessage();
			joinmessage = joinmessage.replace("%user",
					ChatColor.GOLD + player.getName() + ChatColor.WHITE);
			joinmessage = joinmessage.replace("%server", ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.WHITE);
			joinmessage = joinmessage.replace("%core", coremessage);

			timemessage = null;

			timemessage = ConfigLoader.getTimemessage();

			timemessage = timemessage.replace("%time", ChatColor.GOLD + ""
					+ (resthour + ConfigLoader.getTimezone()) + ":"
					+ restminutes + ChatColor.WHITE);

			if (ConfigLoader.getMaintenance()) {

				event.setJoinMessage(ChatColor.RED
						+ ConfigLoader.getMaintenanceMessage());
			} else {

				if (!player.hasPlayedBefore()) {

					event.setJoinMessage(joinmessage.concat(" "
							+ ConfigLoader.getFirstJoinMessage())
							+ " " + timemessage);

					try {
						SpawnArea.tospawn(player);
					} catch (Exception e) {

					}

				} else {

					event.setJoinMessage(joinmessage + " " + timemessage);

				}
			}
		} else {

			if (!player.hasPlayedBefore()) {

				try {
					SpawnArea.tospawn(player);
				} catch (Exception e) {

				}
			} else {
				return;
			}
		}

	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {

		if (ConfigLoader.isMessaging()) {
			leftmessage = null;
			leftmessage = ConfigLoader.getLeftmessage();

			Player player = event.getPlayer();
			leftmessage = leftmessage.replace("%user",
					ChatColor.GOLD + player.getName() + ChatColor.WHITE);
			leftmessage = leftmessage.replace("%server", ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.WHITE);

			event.setQuitMessage(leftmessage);
		} 
		
		if(getIsMod().get(event.getPlayer()) == true ){
			
			Top.doneMe(event.getPlayer());
			getIsMod().remove(event.getPlayer());
		}else{
			SmartServerTool.logger.info(event.getPlayer().getName()+" isn't in Modmode");
		}
		
		return;
	}

	/**
	 * @return the isMod
	 */
	public static HashMap<Player, Boolean> getIsMod() {
		return isMod;
	}

	public static void setIsMOD(HashMap<Player, Boolean> be) {
		isMod = be;
	}

}

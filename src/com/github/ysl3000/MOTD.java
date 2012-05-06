package com.github.ysl3000;

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

	private SmartServerTool plugin;

	private boolean messageing;
	private String firstjoin;
	private String joinmessage;
	private String leftmessage;
	private int timezone;
	private String timemessage;
	private String coremessage;
	private String whitelistmessage;
	private String banmessage;
	private String fullmessage;
	private static boolean maintenance;
	private String maintenance_message;
	private static boolean advert;
	private static long adverttime;
	private static String advertmessage;

	private static boolean glass;
	private static boolean glasspanes;
	private static boolean diamond;
	private static boolean appleDrop;

	public MOTD(SmartServerTool smartServerTool) {
		this.plugin = smartServerTool;
		firstjoin = this.plugin.getConfig().getString("firstjoin");
		joinmessage = this.plugin.getConfig().getString("message");
		leftmessage = this.plugin.getConfig().getString("leftmessage");
		timezone = this.plugin.getConfig().getInt("timezone");
		timemessage = this.plugin.getConfig().getString("timemessage");
		whitelistmessage = this.plugin.getConfig().getString(
				"whitelist-message");
		banmessage = this.plugin.getConfig().getString("banmessage");
		fullmessage = plugin.getConfig().getString("serverfullmessage");
		maintenance = plugin.getConfig().getBoolean("maintenance_mode");
		maintenance_message = plugin.getConfig().getString(
				"maintenance_message");

		advert = plugin.getConfig().getBoolean("plugin-advert");
		adverttime = plugin.getConfig().getLong("time-between-adverts");
		advertmessage = plugin.getConfig().getString("advert-message");

		glass = plugin.getConfig().getBoolean("glass-sand-drop");
		glasspanes = plugin.getConfig().getBoolean("glasspane-drop");
		diamond = plugin.getConfig().getBoolean("diamond-ore-drop");
		appleDrop = plugin.getConfig().getBoolean("golden-apple-drop");
		messageing = plugin.getConfig().getBoolean("enable-Messages");

	}

	public static boolean getappleDrop() {
		return appleDrop;
	}

	public static boolean getDiamondDrop() {
		return diamond;
	}

	public static boolean getadvert() {
		return advert;

	}

	public static long getAdvertTime() {
		return adverttime;
	}

	public static String getAdvertMessage() {
		return advertmessage;
	}

	public static boolean getGlasspaneDrop() {
		return glasspanes;
	}

	public static boolean getGlassSandDrop() {
		return glass;
	}

	@EventHandler
	public void login(PlayerLoginEvent event) {

		if (maintenance) {

			event.setResult(Result.KICK_OTHER);

		}

		if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

			event.setKickMessage(whitelistmessage);
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

			event.setKickMessage(banmessage);
		} else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

			if (event.getPlayer().hasPermission("sst.joinfull")) {

				event.setResult(Result.ALLOWED);
			} else {

				event.setKickMessage(fullmessage);
			}
		} else if (event.getResult() == Result.KICK_OTHER) {

			if (event.getPlayer().hasPermission("sst.joinservice")) {

				event.setResult(Result.ALLOWED);

			} else {

				event.setKickMessage(maintenance_message);
			}
		}

	}

	@EventHandler
	public void serverlistcheck(ServerListPingEvent event) {

		if (maintenance) {

			event.setMaxPlayers(0);
			event.setMotd(maintenance_message);

		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		if (messageing) {

			long second = (System.currentTimeMillis() / 1000);
			long minutes = (second / 60);
			long hour = (minutes / 60);
			long resthour = (hour % 24);
			long restminutes = (minutes % 60);

			Prefix.Pfix(player);

			if (Runtime.getRuntime().availableProcessors() == 1) {

				coremessage = new String(Runtime.getRuntime()
						.availableProcessors() + " core");
			} else {
				coremessage = new String(Runtime.getRuntime()
						.availableProcessors() + " cores");
			}

			joinmessage = null;
			joinmessage = this.plugin.getConfig().getString("message");
			joinmessage = joinmessage.replace("%user",
					ChatColor.GOLD + player.getName() + ChatColor.WHITE);
			joinmessage = joinmessage.replace("%server", ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.WHITE);
			joinmessage = joinmessage.replace("%core", coremessage);

			timemessage = null;

			timemessage = this.plugin.getConfig().getString("timemessage");

			timemessage = timemessage.replace("%time", ChatColor.GOLD + ""
					+ (resthour + timezone) + ":" + restminutes
					+ ChatColor.WHITE);

			if (maintenance) {

				event.setJoinMessage(ChatColor.RED + maintenance_message);
			} else {

				if (!player.hasPlayedBefore()) {

					event.setJoinMessage(joinmessage.concat(" " + firstjoin)
							+ " " + timemessage);

					try {
						Spawnarea.tospawn(player);
					} catch (Exception e) {

					}
					
					if(Spawnarea.getLc() != null){
						player.teleport(Spawnarea.getLc());	
					}else{
						player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(),player.getLocation().getBlockZ());
					}

					

				} else {

					event.setJoinMessage(joinmessage + " " + timemessage);

				}
			}
		} else {
			return;
		}

	}

	@EventHandler
	public void onPlayerLeft(PlayerQuitEvent event) {

		if (messageing) {

			leftmessage = null;
			leftmessage = this.plugin.getConfig().getString("leftmessage");

			Player player = event.getPlayer();
			leftmessage = leftmessage.replace("%user",
					ChatColor.GOLD + player.getName() + ChatColor.WHITE);
			leftmessage = leftmessage.replace("%server", ChatColor.GREEN
					+ Bukkit.getServerName() + ChatColor.WHITE);

			event.setQuitMessage(leftmessage);
		} else {
			return;
		}
	}

}

package com.ysl3000.events;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.config.settings.Misc;
import com.ysl3000.config.settings.PlayerMessage;
import com.ysl3000.config.settings.Service;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.Utility;
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

  private Utility utility;
  private PlayerMessage playerMessage;
  private Service service;
  private Messages messages;
  private Prefix prefix;
  private Misc misc;

  public MOTD(Utility utility, Messages messages, Prefix prefix, Misc misc) {
    this.utility = utility;
    this.playerMessage = messages.getPlayer();
    this.messages = messages;
    this.service = messages.getService();
    this.prefix = prefix;
    this.misc = misc;
  }

  @EventHandler
  public void login(PlayerLoginEvent event) {

    if (service.isUnderConstruction()) {
      event.setResult(Result.KICK_OTHER);
    }
    if (event.getResult() == PlayerLoginEvent.Result.KICK_WHITELIST) {

      event.setKickMessage(service.getWhitelist());

      messageTryingToJoin(event.getPlayer().getDisplayName(), event
          .getResult().name().substring(5, 14));
    } else if (event.getResult() == PlayerLoginEvent.Result.KICK_BANNED) {

      event.setKickMessage(service.getBan());
      messageTryingToJoin(event.getPlayer().getDisplayName(), event
          .getResult().name().substring(5, 11));
    } else if (event.getResult() == PlayerLoginEvent.Result.KICK_FULL) {

      if (event.getPlayer().hasPermission(Permissions.joinFull)) {

        event.setResult(Result.ALLOWED);
      } else {

        event.setKickMessage(service.getServerfull());
      }
    } else if (event.getResult() == Result.KICK_OTHER) {

      if (event.getPlayer().hasPermission(Permissions.joinService)) {

        event.setResult(Result.ALLOWED);

      } else {

        event.setKickMessage(ChatColor.DARK_RED
            + service.getConstruction());
      }
    }

  }

  private void messageTryingToJoin(String name, String type) {
    Bukkit.broadcast(name + "{" + type + "}" + " trying to join",
        "sst.admin");
  }

  @EventHandler
  public void serverlistcheck(ServerListPingEvent event) {

    if (service.isUnderConstruction()) {

      event.setMaxPlayers(-2);
      event.setMotd(ChatColor.DARK_RED
          + service.getConstruction());

    } else {
      event.setMotd(ChatColor.GOLD + event.getMotd());
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();

    // todo refactor build message elsewhere

    String coremessage;
    String privateJoinmessage;
    String joinmessage;

    if (messages.isEnabled()) {


    }

    player.setSleepingIgnored(misc.isSleepingIgnored());
    prefix.setPrefixAndListName(player);
  }

  @EventHandler
  public void onPlayerLeft(PlayerQuitEvent event) {
    if (messages.isEnabled()) {
      String leftmessage = playerMessage.getLeftMessage();

      Player player = event.getPlayer();
      leftmessage = leftmessage.replace("user%", ChatColor.GOLD
          + player.getName() + ChatColor.WHITE);
      leftmessage = leftmessage.replace("server%", ChatColor.GREEN
          + Bukkit.getServerName() + ChatColor.WHITE);

      event.setQuitMessage(leftmessage);
    }
  }

}

package com.ysl3000.events;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.config.settings.Misc;
import com.ysl3000.config.settings.messages.PlayerMessage;
import com.ysl3000.config.settings.messages.Service;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Permissions;
import com.ysl3000.utils.Prefix;
import com.ysl3000.utils.valuemappers.MessageBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {

  private PlayerMessage playerMessage;
  private Service service;
  private Messages messages;
  private Prefix prefix;
  private Misc misc;

  private Server server;

  private MessageBuilder messageBuilder;

  public MOTD(Messages messages, Prefix prefix, Misc misc,
      Server server, MessageBuilder messageBuilder) {
    this.playerMessage = messages.getPlayer();
    this.messages = messages;
    this.service = messages.getService();
    this.prefix = prefix;
    this.misc = misc;
    this.server = server;
    this.messageBuilder = messageBuilder;
  }

  @EventHandler
  public void login(PlayerLoginEvent event) {

    if (service.isUnderConstruction()) {
      event.setResult(Result.KICK_OTHER);
    }

    switch (event.getResult()) {
      case KICK_WHITELIST:
        event.setKickMessage(service.getWhitelist());
        Bukkit.broadcast(messageBuilder.replaceMessageValues(MessageWrapper
            .of(playerMessage.getTryingToJoinMessage(), event.getResult(), event.getPlayer()))
            .getMessage(), "sst.admin");
        break;
      case KICK_BANNED:
        event.setKickMessage(service.getBan());
        Bukkit.broadcast(messageBuilder.replaceMessageValues(MessageWrapper
            .of(playerMessage.getTryingToJoinMessage(), event.getResult(), event.getPlayer()))
            .getMessage(), "sst.admin");
        break;
      case ALLOWED:
        break;
      case KICK_FULL:
        if (event.getPlayer().hasPermission(Permissions.joinFull)) {
          event.setResult(Result.ALLOWED);
        } else {
          event.setKickMessage(service.getServerfull());
        }
        break;
      case KICK_OTHER:
        if (event.getPlayer().hasPermission(Permissions.joinService)) {
          event.setResult(Result.ALLOWED);
        } else {
          event.setKickMessage(
              ChatColor.translateAlternateColorCodes('&', service.getConstruction()));
        }
        break;
    }

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

    player.setSleepingIgnored(misc.isSleepingIgnored());

    if (messages.isEnabled()) {

      if (messages.isEnablbeRandomChatColor()) {
        prefix.setPrefixAndListName(player);
      }

      if (service.isUnderConstruction()) {
        for (Player p : Bukkit.getOnlinePlayers()) {
          p.sendMessage(service.getConstruction());
        }

      } else {

        String joinmessage = messageBuilder.replaceMessageValues(
            MessageWrapper.of(playerMessage.getJoinMessage(), player)
        ).getMessage();

        server.getOnlinePlayers().forEach(p -> p.sendMessage(joinmessage));
        event.setJoinMessage("");
      }

      String privateJoinmessage = messageBuilder.replaceMessageValues(
          MessageWrapper.of(playerMessage.getPrivateJoinMessage())
      ).getMessage();
      player.sendMessage(privateJoinmessage);
    }

    if (!player.hasPlayedBefore()) {
      player.teleport(
          player.getWorld().getSpawnLocation());
    }

    prefix.setPrefixAndListName(player);
  }

  @EventHandler
  public void onPlayerLeft(PlayerQuitEvent event) {
    if (messages.isEnabled()) {
      MessageWrapper leftMessageWrapper = MessageWrapper
          .of(playerMessage.getLeftMessage(), event.getPlayer());
      messageBuilder.replaceMessageValues(leftMessageWrapper);
      event.setQuitMessage(leftMessageWrapper.getMessage());
    }
  }
}

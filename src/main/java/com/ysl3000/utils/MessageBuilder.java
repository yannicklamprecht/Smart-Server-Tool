package com.ysl3000.utils;

import com.ysl3000.utils.valuemappers.BukkitVersionMapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class MessageBuilder {

  private List<ValueMapper> mappers = new ArrayList<>();

  public MessageBuilder(Server server){

    mappers.add(new BukkitVersionMapper(server));

  }


  public void dosth(){

    if (messages.isEnablbeRandomChatColor()) {
      prefix.setPrefixAndListName(player);
    }

    if (Runtime.getRuntime().availableProcessors() == 1) {

      coremessage = Runtime.getRuntime()
          .availableProcessors() + " core";
    } else {
      coremessage = Runtime.getRuntime()
          .availableProcessors() + " cores";
    }

    joinmessage = playerMessage.getJoinMessage();
    privateJoinmessage = playerMessage.getPrivateJoinMessage();






    privateJoinmessage = privateJoinmessage.replace("online%",
        ChatColor.GRAY + "Online ("
            + Bukkit.getServer().getOnlinePlayers().size()
            + "/" + Bukkit.getMaxPlayers() + "): "
            + utility.listPlayers());
    privateJoinmessage = privateJoinmessage.replace("n%", "\n");

    if (service.isUnderConstruction()) {

      for (Player p : Bukkit.getOnlinePlayers()) {
        p.sendMessage(service.getConstruction());
      }

      event.setJoinMessage("");
    } else {

      if (!player.hasPlayedBefore()) {

        for (Player p : Bukkit.getOnlinePlayers()) {
          p.sendMessage(joinmessage.concat(" " + playerMessage.getFirstJoin()));
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


  private String buildJoinMessage(String joinmessage){
    joinmessage = joinmessage.replace("user%", "" + ChatColor.GOLD
        + player.getName() + ChatColor.WHITE);
    joinmessage = joinmessage.replace("server%", ""
        + ChatColor.GREEN + Bukkit.getServerName()
        + ChatColor.WHITE);

    joinmessage = joinmessage.replace("core%", "" + coremessage);

    joinmessage = joinmessage.replace(
        "time%", utility.getTime(
            System.currentTimeMillis(),
            messages.getTimeformat().replace("%dp", ":")));

    joinmessage = .replaceValue(joinmessage);
    joinmessage = joinmessage.replace("v%", Bukkit.getVersion().substring(11, 16));
    joinmessage = joinmessage.replace("b%", Bukkit.getBukkitVersion());

    return joinmessage;
  }

}

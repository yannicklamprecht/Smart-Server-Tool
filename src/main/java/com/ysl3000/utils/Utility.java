package com.ysl3000.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utility {

  public void tospawn(Player player) {
    player.teleport(player.getWorld().getSpawnLocation());
  }

  public String getTime(long time, String format) {
    return new SimpleDateFormat(format).format(new Date(time));
  }

  public String listPlayers() {
    return String.join(",",
        Bukkit.getOnlinePlayers().stream().map(Player::getDisplayName).collect(Collectors.toSet())
    );
  }
}

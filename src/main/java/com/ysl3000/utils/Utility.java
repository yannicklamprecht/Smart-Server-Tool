package com.ysl3000.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Utility {


  private Server server;

  public Utility(Server server) {
    this.server = server;
  }

  public void tospawn(Player player) {
    player.teleport(player.getWorld().getSpawnLocation());
  }

  public String getTime(long time, String format) {
    return new SimpleDateFormat(format).format(new Date(time));
  }

  public String listPlayers() {
    return String.join(",",
        server.getOnlinePlayers().stream().map(Player::getDisplayName).collect(Collectors.toSet())
    );
  }
}

package com.ysl3000;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class SmartPlayers {

  private Map<UUID, SmartPlayer> players = new HashMap<>();


  public Optional<SmartPlayer> getPlayerByUUID(UUID playerUUID) {
    return Optional.ofNullable(players.get(playerUUID));
  }

  public void add(Player player) {
    players.put(player.getUniqueId(), new SmartPlayer(player));
  }

  public void remove(Player player) {
    players.remove(player.getUniqueId());
  }


}

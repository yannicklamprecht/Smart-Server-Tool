package com.ysl3000;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class SmartPlayers {

  private Cache<UUID, SmartPlayer> playerCache;

  public SmartPlayers(){

    this.playerCache = CacheBuilder.newBuilder()
        .maximumSize(10000)
        .expireAfterAccess(1, TimeUnit.HOURS)
        .build();

  }


  public SmartPlayer getPlayerByUUID(Player player) throws ExecutionException {
    return playerCache.get(player.getUniqueId(), () -> new SmartPlayer(player));
  }

  public void add(Player player) {
    playerCache.put(player.getUniqueId(), new SmartPlayer(player));
  }

  public void remove(Player player) {
    playerCache.invalidate(player.getUniqueId());
  }


}

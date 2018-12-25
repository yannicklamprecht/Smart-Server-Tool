package com.ysl3000.plugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ysl3000
 */
public class SmartPlayers {

    private Map<UUID, SmartPlayer> players = new HashMap<>();


    public SmartPlayer getPlayerByUUID(UUID playerUUID){
        return players.get(playerUUID);
    }

    public void add(Player player){
        players.put(player.getUniqueId(), new SmartPlayer(player));
    }

    public void remove(Player player){
        players.remove(player.getUniqueId());
    }


}

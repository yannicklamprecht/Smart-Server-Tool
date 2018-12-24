package com.ysl3000.events;

import com.ysl3000.plugin.SmartPlayers;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by ysl3000
 */
public class PlayerConnectionListener implements Listener {

    private SmartPlayers smartPlayers;

    public PlayerConnectionListener(SmartPlayers smartPlayers) {
        this.smartPlayers = smartPlayers;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent){
        smartPlayers.add(playerJoinEvent.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent){
        smartPlayers.remove(playerQuitEvent.getPlayer());
    }

}

package com.ysl3000.events;

import com.ysl3000.config.data.WorldSpawnWrapper;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;

/**
 * Created by ysl3000
 */
public class WorldSpawnListener implements Listener {

  private final WorldSpawnWrapper worldSpawnWrapper;

  public WorldSpawnListener(WorldSpawnWrapper worldSpawnWrapper) {
    this.worldSpawnWrapper = worldSpawnWrapper;
  }

  @EventHandler
  public void onWorldLoad(WorldLoadEvent worldLoadEvent) {
    World world = worldLoadEvent.getWorld();
    world.setSpawnLocation(worldSpawnWrapper.getSpawnpointForWorld(world.getSpawnLocation()));
  }


  @EventHandler
  public void onWorldUnload(WorldSaveEvent worldSaveEvent) {
    worldSpawnWrapper.setSpawnPointForWorld(worldSaveEvent.getWorld().getSpawnLocation());
  }
}

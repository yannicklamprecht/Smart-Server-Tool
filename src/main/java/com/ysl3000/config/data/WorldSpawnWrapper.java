package com.ysl3000.config.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Location;

/**
 * Created by ysl3000
 */
public class WorldSpawnWrapper {

  private Map<String, SpawnLocation> locations = new HashMap<>();


  public WorldSpawnWrapper(WorldSpawnLocation worldSpawnLocation) {
    for (SpawnLocation spawnLocation : worldSpawnLocation.getWorldSpawns()) {
      locations.put(spawnLocation.getWorldName(), spawnLocation);
    }

  }

  public Location getSpawnpointForWorld(Location location) {
    if (location == null) {
      return null;
    }

    SpawnLocation spawnLocation = locations.get(location.getWorld().getName());
    if (spawnLocation == null) {
      locations
          .put(location.getWorld().getName(), SpawnLocation.fromLocation(location));
      return location;
    }

    return SpawnLocation
        .toLocation(location.getWorld(), spawnLocation);
  }

  public void setSpawnPointForWorld(Location location) {
    this.locations
        .put(location.getWorld().getName(), SpawnLocation.fromLocation(location));
  }

  public String getSpawnLocationsAsString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Entry<String, SpawnLocation> spawn : locations.entrySet()) {
      stringBuilder.append(spawn.getKey()).append(": ").append(spawn.getValue()).append("\n");
    }
    return stringBuilder.toString();
  }

  public WorldSpawnLocation getWorldSpawnLocation() {
    WorldSpawnLocation worldSpawnLocation = new WorldSpawnLocation();
    locations.forEach((key, value) -> worldSpawnLocation.getWorldSpawns().add(value));
    return worldSpawnLocation;
  }
}

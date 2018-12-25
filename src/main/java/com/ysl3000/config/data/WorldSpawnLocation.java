package com.ysl3000.config.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ysl3000
 */
public class WorldSpawnLocation {

  private Map<String, ConfigPosition> worldSpawns = new HashMap<>();

  public WorldSpawnLocation() {
    worldSpawns.put("world", new ConfigPosition());
  }


  public Map<String, ConfigPosition> getWorldSpawns() {
    return worldSpawns;
  }

  public void setWorldSpawns(Map<String, ConfigPosition> worldSpawns) {
    this.worldSpawns = worldSpawns;
  }


  public ConfigPosition getSpawnpointForWorld(String worldName) {
    return worldSpawns.get(worldName);
  }

  public void setSpawnPointForWorld(String worldName, ConfigPosition position) {
    worldSpawns.put(worldName, position);
  }
}

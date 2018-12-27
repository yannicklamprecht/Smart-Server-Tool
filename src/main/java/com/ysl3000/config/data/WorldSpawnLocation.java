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


  public IConfigPosition getSpawnpointForWorld(String worldName) {
    return worldSpawns.computeIfAbsent(worldName,
        worldNameAbsent -> new ConfigPosition(worldNameAbsent, 0, 0, 0));
  }

  public void setSpawnPointForWorld(String worldName, IConfigPosition position) {
    ConfigPosition configPosition = new ConfigPosition();
    configPosition.copyOf(position);
    worldSpawns.put(worldName, configPosition);
  }
}

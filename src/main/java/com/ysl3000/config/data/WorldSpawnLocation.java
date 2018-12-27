package com.ysl3000.config.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ysl3000
 */
public class WorldSpawnLocation {

  private Map<String, IConfigPosition> worldSpawns = new HashMap<>();

  public WorldSpawnLocation() {
    worldSpawns.put("world", new ConfigPosition());
  }


  public Map<String, IConfigPosition> getWorldSpawns() {
    return worldSpawns;
  }

  public void setWorldSpawns(Map<String, IConfigPosition> worldSpawns) {
    this.worldSpawns = worldSpawns;
  }


  public IConfigPosition getSpawnpointForWorld(String worldName) {
    return worldSpawns.computeIfAbsent(worldName,worldNameAbsent -> new ConfigPosition(worldNameAbsent, 0, 0, 0));
  }

  public void setSpawnPointForWorld(String worldName, IConfigPosition position) {
    worldSpawns.put(worldName, position);
  }
}

package com.ysl3000.config.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ysl3000
 */
public class WorldSpawnLocation {

  private List<SpawnLocation> worldSpawns = new ArrayList<>();


  public List<SpawnLocation> getWorldSpawns() {
    return worldSpawns;
  }

  public void setWorldSpawns(List<SpawnLocation> worldSpawns) {
    this.worldSpawns = worldSpawns;
  }

}

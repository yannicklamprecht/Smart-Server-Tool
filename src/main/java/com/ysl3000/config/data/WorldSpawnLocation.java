package com.ysl3000.config.data;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ysl3000
 */
public class WorldSpawnLocation {

    private Map<String, Location> worldSpawns = new HashMap<>();


    public Map<String, Location> getWorldSpawns() {
        return worldSpawns;
    }

    public void setWorldSpawns(Map<String, Location> worldSpawns) {
        this.worldSpawns = worldSpawns;
    }


    public Location getSpawnpointForWorld(World world) {
        return worldSpawns.get(world.getName());
    }

    public void setSpawnPointForWorld(Location spawnPoint) {
        worldSpawns.put(spawnPoint.getWorld().getName(), spawnPoint);
    }
}

package com.ysl3000.config.data;


import org.bukkit.Location;
import org.bukkit.World;

/**
 * Created by ysl3000
 */
public class SpawnLocation {

  private String worldName;
  private double x;
  private double y;
  private double z;
  private float yaw;
  private float pitch;


  public SpawnLocation() {
    this("world", 0, 0, 0, 0, 0);
  }


  public SpawnLocation(String worldName, double x, double y, double z, float yaw, float pitch) {
    this.worldName = worldName;
    this.x = x;
    this.y = y;
    this.z = z;
    this.yaw = yaw;
    this.pitch = pitch;
  }

  public static Location toLocation(World world, SpawnLocation spawnLocation) {
    return new Location(world, spawnLocation.x, spawnLocation.y,
        spawnLocation.z, spawnLocation.yaw, spawnLocation.pitch);
  }

  public static SpawnLocation fromLocation(Location location) {
    return new SpawnLocation(location.getWorld().getName(), location.getX(), location.getY(),
        location.getZ(), location.getYaw(), location.getPitch());
  }

  @Override
  public String toString() {
    return "SpawnLocation{" +
        "worldName='" + worldName + '\'' +
        ", x=" + x +
        ", y=" + y +
        ", z=" + z +
        ", yaw=" + yaw +
        ", pitch=" + pitch +
        '}';
  }

  public String getWorldName() {
    return worldName;
  }

  public void setWorldName(String worldName) {
    this.worldName = worldName;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getZ() {
    return z;
  }

  public void setZ(double z) {
    this.z = z;
  }

  public float getPitch() {
    return pitch;
  }

  public void setPitch(float pitch) {
    this.pitch = pitch;
  }

  public float getYaw() {
    return yaw;
  }

  public void setYaw(float yaw) {
    this.yaw = yaw;
  }
}

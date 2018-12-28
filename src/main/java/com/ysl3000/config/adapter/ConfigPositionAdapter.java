package com.ysl3000.config.adapter;

import com.ysl3000.config.data.ConfigPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by ysl3000
 */
public class ConfigPositionAdapter extends ConfigPosition {


  private Location location;

  public ConfigPositionAdapter(Location location) {
    this.location = location;
  }

  @Override
  public double getX() {
    return location.getX();
  }

  @Override
  public void setX(double x) {
    location.setX(x);
  }

  @Override
  public double getY() {
    return location.getY();
  }

  @Override
  public void setY(double y) {
    location.setY(y);
  }

  @Override
  public double getZ() {
    return location.getZ();
  }

  @Override
  public void setZ(double z) {
    location.setZ(z);
  }

  @Override
  public String getName() {
    return location.getWorld().getName();
  }

  @Override
  public void setName(String name) {
    location.setWorld(Bukkit.getWorld(name));
  }

}

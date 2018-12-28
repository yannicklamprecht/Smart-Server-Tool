package com.ysl3000.config.adapter;


import com.ysl3000.config.data.IConfigPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by ysl3000
 */
public class LocationAdapter extends Location {

  private IConfigPosition configPosition;

  public LocationAdapter(IConfigPosition configPosition) {
    super(Bukkit.getWorld(configPosition.getName()), configPosition.getX(),configPosition.getY(),configPosition.getZ());
    this.configPosition = configPosition;
  }

  @Override
  public double getX() {
    return configPosition.getX();
  }

  @Override
  public void setX(double x) {
    configPosition.setX(x);
  }

  @Override
  public double getY() {
    return configPosition.getY();
  }

  @Override
  public void setY(double y) {
    configPosition.setY(y);
  }

  @Override
  public double getZ() {
    return configPosition.getZ();
  }

  @Override
  public void setZ(double z) {
    configPosition.setZ(z);
  }
}

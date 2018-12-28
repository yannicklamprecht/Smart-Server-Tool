package com.ysl3000.config.data;

import java.util.Objects;

/**
 * Created by ysl3000
 */
public class ConfigPosition implements IConfigPosition {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ConfigPosition)) {
      return false;
    }
    ConfigPosition that = (ConfigPosition) o;
    return Double.compare(that.x, x) == 0 &&
        Double.compare(that.y, y) == 0 &&
        Double.compare(that.z, z) == 0 &&
        name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, x, y, z);
  }

  private String name;
  private double x;
  private double y;
  private double z;

  public ConfigPosition(){
    this("world",0,0,0);
  }

  public ConfigPosition(String worldName, double x, double y, double z){
    name = worldName;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public double getX() {
    return x;
  }
  @Override
  public void setX(double x) {
    this.x = x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public double getZ() {
    return z;
  }
  @Override
  public void setZ(double z) {
    this.z = z;
  }
  @Override
  public String getName() {
    return name;
  }
  @Override
  public void setName(String name) {
    this.name = name;
  }

}

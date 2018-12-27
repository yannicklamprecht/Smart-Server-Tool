package com.ysl3000.config.data;

/**
 * Created by ysl3000
 */
public class ConfigPosition implements IConfigPosition {

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

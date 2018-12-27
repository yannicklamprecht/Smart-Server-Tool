package com.ysl3000.config.data;

/**
 * Created by ysl3000
 */
public interface IConfigPosition {

  default void copyOf(IConfigPosition configPosition) {
    this.setName(configPosition.getName());
    this.setX(configPosition.getX());
    this.setY(configPosition.getY());
    this.setZ(configPosition.getZ());
  }

  double getX();

  void setX(double x);

  double getY();

  void setY(double y);

  double getZ();

  void setZ(double z);

  String getName();

  void setName(String name);
}

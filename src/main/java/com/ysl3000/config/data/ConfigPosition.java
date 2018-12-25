package com.ysl3000.config.data;

/**
 * Created by ysl3000
 */
public class ConfigPosition {

    private String name = "world";
    private double x = 0;
    private double y = 0;
    private double z = 0;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

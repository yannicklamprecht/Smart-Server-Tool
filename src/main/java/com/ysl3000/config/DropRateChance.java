package com.ysl3000.config;

/**
 * Created by ysl3000
 */
public class DropRateChance {

    private int diamond = 3;
    private int glassSand = 1;
    private int glassPane = 1; // could be mc intern

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getGlassSand() {
        return glassSand;
    }

    public void setGlassSand(int glassSand) {
        this.glassSand = glassSand;
    }

    public int getGlassPane() {
        return glassPane;
    }

    public void setGlassPane(int glassPane) {
        this.glassPane = glassPane;
    }
}

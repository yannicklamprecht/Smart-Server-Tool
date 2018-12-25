package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class DropsSettings {

    private boolean glassSand= true;
    private boolean glassPane = true;
    private boolean diamondOre = true;
    private boolean goldenAppleShear = true;

    public boolean isGlassSand() {
        return glassSand;
    }

    public void setGlassSand(boolean glassSand) {
        this.glassSand = glassSand;
    }

    public boolean isGlassPane() {
        return glassPane;
    }

    public void setGlassPane(boolean glassPane) {
        this.glassPane = glassPane;
    }

    public boolean isDiamondOre() {
        return diamondOre;
    }

    public void setDiamondOre(boolean diamondOre) {
        this.diamondOre = diamondOre;
    }

    public boolean isGoldenAppleShear() {
        return goldenAppleShear;
    }

    public void setGoldenAppleShear(boolean goldenAppleShear) {
        this.goldenAppleShear = goldenAppleShear;
    }
}

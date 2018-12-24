package com.ysl3000.config;

/**
 * Created by ysl3000
 */
public class SmartSettings {

    private boolean physicsSand = false;
    private boolean physicsTorch = false;
    private boolean physicsGravel = false;
    private boolean noPermissionsNeeded = false;
    private boolean fireSpread = false;
    private boolean lightningSpread = false;
    private boolean normalSpread = false;
    private boolean lavaSpread = false;

    private Messages messages = new Messages();
    private DropsSettings drops = new DropsSettings();
    private DropRateChance chance = new DropRateChance();

    public boolean isPhysicsSand() {
        return physicsSand;
    }

    public void setPhysicsSand(boolean physicsSand) {
        this.physicsSand = physicsSand;
    }

    public boolean isPhysicsTorch() {
        return physicsTorch;
    }

    public void setPhysicsTorch(boolean physicsTorch) {
        this.physicsTorch = physicsTorch;
    }

    public boolean isPhysicsGravel() {
        return physicsGravel;
    }

    public void setPhysicsGravel(boolean physicsGravel) {
        this.physicsGravel = physicsGravel;
    }

    public boolean isNoPermissionsNeeded() {
        return noPermissionsNeeded;
    }

    public void setNoPermissionsNeeded(boolean noPermissionsNeeded) {
        this.noPermissionsNeeded = noPermissionsNeeded;
    }

    public boolean isFireSpread() {
        return fireSpread;
    }

    public void setFireSpread(boolean fireSpread) {
        this.fireSpread = fireSpread;
    }

    public boolean isLightningSpread() {
        return lightningSpread;
    }

    public void setLightningSpread(boolean lightningSpread) {
        this.lightningSpread = lightningSpread;
    }

    public boolean isNormalSpread() {
        return normalSpread;
    }

    public void setNormalSpread(boolean normalSpread) {
        this.normalSpread = normalSpread;
    }

    public boolean isLavaSpread() {
        return lavaSpread;
    }

    public void setLavaSpread(boolean lavaSpread) {
        this.lavaSpread = lavaSpread;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public DropRateChance getChance() {
        return chance;
    }

    public void setChance(DropRateChance chance) {
        this.chance = chance;
    }

    public DropsSettings getDrops() {
        return drops;
    }

    public void setDrops(DropsSettings drops) {
        this.drops = drops;
    }
}

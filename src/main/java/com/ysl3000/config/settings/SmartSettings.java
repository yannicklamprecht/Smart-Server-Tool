package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class SmartSettings {

  private boolean physicsSand = false;
  private boolean physicsTorch = false;
  private boolean physicsGravel = false;
  private boolean noPermissionsNeeded = false;


  private Messages messages = new Messages();
  private DropsSettings drops = new DropsSettings();
  private DropRateChance chance = new DropRateChance();
  private WorldSettings worldSettings = new WorldSettings();
  private Misc misc = new Misc();
  private AdvertisingSettings advertisingSettings = new AdvertisingSettings();

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

  public WorldSettings getWorldSettings() {
    return worldSettings;
  }

  public void setWorldSettings(WorldSettings worldSettings) {
    this.worldSettings = worldSettings;
  }

  public Misc getMisc() {
    return misc;
  }

  public void setMisc(Misc misc) {
    this.misc = misc;
  }

  public AdvertisingSettings getAdvertisingSettings() {
    return advertisingSettings;
  }

  public void setAdvertisingSettings(AdvertisingSettings advertisingSettings) {
    this.advertisingSettings = advertisingSettings;
  }
}

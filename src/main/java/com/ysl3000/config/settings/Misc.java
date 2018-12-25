package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class Misc {


  private boolean savePlayerPressPlate = true;
  private boolean sleepingIgnored = true;
  private boolean enableRandomChatcolor = true;
  private int defaultStackSize = 64;


  public boolean isSavePlayerPressPlate() {
    return savePlayerPressPlate;
  }

  public void setSavePlayerPressPlate(boolean savePlayerPressPlate) {
    this.savePlayerPressPlate = savePlayerPressPlate;
  }

  public boolean isSleepingIgnored() {
    return sleepingIgnored;
  }

  public void setSleepingIgnored(boolean sleepingIgnored) {
    this.sleepingIgnored = sleepingIgnored;
  }

  public boolean isEnableRandomChatcolor() {
    return enableRandomChatcolor;
  }

  public void setEnableRandomChatcolor(boolean enableRandomChatcolor) {
    this.enableRandomChatcolor = enableRandomChatcolor;
  }

  public int getDefaultStackSize() {
    return defaultStackSize;
  }

  public void setDefaultStackSize(int defaultStackSize) {
    this.defaultStackSize = defaultStackSize;
  }
}

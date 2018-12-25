package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class WorldSettings {

  private boolean blockCreeper = true;
  private boolean preventTnt = true;
  private boolean blockEnder = true;
  private boolean xpSave = true;
  private boolean preventFireSpread = true;
  private boolean preventLavaSpread = true;
  private boolean generalSpread = true;
  private boolean flintAndStealSpread = true;
  private boolean strikeSpread = true;


  public boolean isBlockCreeper() {
    return blockCreeper;
  }

  public void setBlockCreeper(boolean blockCreeper) {
    this.blockCreeper = blockCreeper;
  }

  public boolean isPreventTnt() {
    return preventTnt;
  }

  public void setPreventTnt(boolean preventTnt) {
    this.preventTnt = preventTnt;
  }

  public boolean isBlockEnder() {
    return blockEnder;
  }

  public void setBlockEnder(boolean blockEnder) {
    this.blockEnder = blockEnder;
  }

  public boolean isXpSave() {
    return xpSave;
  }

  public void setXpSave(boolean xpSave) {
    this.xpSave = xpSave;
  }

  public boolean isPreventFireSpread() {
    return preventFireSpread;
  }

  public void setPreventFireSpread(boolean preventFireSpread) {
    this.preventFireSpread = preventFireSpread;
  }

  public boolean isPreventLavaSpread() {
    return preventLavaSpread;
  }

  public void setPreventLavaSpread(boolean preventLavaSpread) {
    this.preventLavaSpread = preventLavaSpread;
  }

  public boolean isGeneralSpread() {
    return generalSpread;
  }

  public void setGeneralSpread(boolean generalSpread) {
    this.generalSpread = generalSpread;
  }

  public boolean isFlintAndStealSpread() {
    return flintAndStealSpread;
  }

  public void setFlintAndStealSpread(boolean flintAndStealSpread) {
    this.flintAndStealSpread = flintAndStealSpread;
  }

  public boolean isStrikeSpread() {
    return strikeSpread;
  }

  public void setStrikeSpread(boolean strikeSpread) {
    this.strikeSpread = strikeSpread;
  }
}

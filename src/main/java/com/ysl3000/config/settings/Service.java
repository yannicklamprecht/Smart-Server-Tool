package com.ysl3000.config.settings;

/**
 * Created by ysl3000
 */
public class Service {

  private String whitelist = "You aren't whitelisted on this server";
  private String ban = "You are a Bunny! You got banned from this server";
  private String serverfull = "I'm sorry! The Server is full!";
  private boolean isUnderConstruction = false;
  private String construction = "Server is under construction";

  public String getWhitelist() {
    return whitelist;
  }

  public void setWhitelist(String whitelist) {
    this.whitelist = whitelist;
  }

  public String getBan() {
    return ban;
  }

  public void setBan(String ban) {
    this.ban = ban;
  }

  public String getServerfull() {
    return serverfull;
  }

  public void setServerfull(String serverfull) {
    this.serverfull = serverfull;
  }

  public boolean isUnderConstruction() {
    return isUnderConstruction;
  }

  public void setUnderConstruction(boolean underConstruction) {
    isUnderConstruction = underConstruction;
  }

  public String getConstruction() {
    return construction;
  }

  public void setConstruction(String construction) {
    this.construction = construction;
  }
}

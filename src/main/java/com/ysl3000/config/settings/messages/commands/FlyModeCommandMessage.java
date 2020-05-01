package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class FlyModeCommandMessage extends CommandConfig {

  private String on = "You can now fly ";
  private String off = "Fly is now disabled";
  private String senderOtherOn = "Set fly on for {player_display_name}";
  private String senderOtherOff = "Set fly off for {player_display_name}";
  private String targetOtherOn = "You can now fly! Allowed by {player_display_name}";
  private String targetOtherOff = "Until now you have to walk on feet! Disallowed by {player_display_name}";
  private String targetMustBeOnGround = "{player_display_name} is flying! Only if player is on earth you can disble that!";
  private String noPermissionToFlyOther = "No permission for flying others";

  public FlyModeCommandMessage() {
    super("fly", "toggle fly", "/fly <player>",
        "sst.fly");
  }

  public String getOn() {
    return on;
  }

  public void setOn(String on) {
    this.on = on;
  }

  public String getOff() {
    return off;
  }

  public void setOff(String off) {
    this.off = off;
  }

  public String getSenderOtherOn() {
    return senderOtherOn;
  }

  public void setSenderOtherOn(String senderOtherOn) {
    this.senderOtherOn = senderOtherOn;
  }

  public String getSenderOtherOff() {
    return senderOtherOff;
  }

  public void setSenderOtherOff(String senderOtherOff) {
    this.senderOtherOff = senderOtherOff;
  }

  public String getTargetOtherOn() {
    return targetOtherOn;
  }

  public void setTargetOtherOn(String targetOtherOn) {
    this.targetOtherOn = targetOtherOn;
  }

  public String getTargetOtherOff() {
    return targetOtherOff;
  }

  public void setTargetOtherOff(String targetOtherOff) {
    this.targetOtherOff = targetOtherOff;
  }

  public String getTargetMustBeOnGround() {
    return targetMustBeOnGround;
  }

  public void setTargetMustBeOnGround(String targetMustBeOnGround) {
    this.targetMustBeOnGround = targetMustBeOnGround;
  }

  public String getNoPermissionToFlyOther() {
    return noPermissionToFlyOther;
  }

  public void setNoPermissionToFlyOther(String noPermissionToFlyOther) {
    this.noPermissionToFlyOther = noPermissionToFlyOther;
  }
}

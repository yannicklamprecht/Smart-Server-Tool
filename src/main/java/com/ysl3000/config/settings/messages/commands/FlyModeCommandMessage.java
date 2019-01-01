package com.ysl3000.config.settings.messages.commands;

import com.ysl3000.config.settings.CommandConfig;
import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
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
}

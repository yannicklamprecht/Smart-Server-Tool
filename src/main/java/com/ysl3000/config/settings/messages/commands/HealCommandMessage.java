package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class HealCommandMessage extends CommandConfig {

  private String permissionHealOther = "sst.heal.other";
  private String youHealed = "&aYou healed &5{parameter}";
  private String noPlayerFoundWithThatName = "No players with that names found!";
  private String healedSelf = "&aHealed!";
  private String healedBySomeone = "&aYou have been healed by {player_display_name}";

  public HealCommandMessage() {
    super("heal", "heal someone", "/heal <player>",
        "sst.heal");
  }
}

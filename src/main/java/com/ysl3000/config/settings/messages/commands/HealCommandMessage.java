package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
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

  public String getPermissionHealOther() {
    return permissionHealOther;
  }

  public void setPermissionHealOther(String permissionHealOther) {
    this.permissionHealOther = permissionHealOther;
  }

  public String getYouHealed() {
    return youHealed;
  }

  public void setYouHealed(String youHealed) {
    this.youHealed = youHealed;
  }

  public String getNoPlayerFoundWithThatName() {
    return noPlayerFoundWithThatName;
  }

  public void setNoPlayerFoundWithThatName(String noPlayerFoundWithThatName) {
    this.noPlayerFoundWithThatName = noPlayerFoundWithThatName;
  }

  public String getHealedSelf() {
    return healedSelf;
  }

  public void setHealedSelf(String healedSelf) {
    this.healedSelf = healedSelf;
  }

  public String getHealedBySomeone() {
    return healedBySomeone;
  }

  public void setHealedBySomeone(String healedBySomeone) {
    this.healedBySomeone = healedBySomeone;
  }
}

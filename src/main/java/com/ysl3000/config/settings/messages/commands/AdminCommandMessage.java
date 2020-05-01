package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class AdminCommandMessage extends CommandConfig {

  private String operatorDisabled = "&4Op disabled";
  private String operatorEnabled = "&aOp enabled";
  private String pleaseUseDeopPlayer = "Please use /deop <player>";

  public AdminCommandMessage() {
    super("/admin", "Toggle op", "//admin", "sst.admin");
  }

  public String getOperatorDisabled() {
    return operatorDisabled;
  }

  public void setOperatorDisabled(String operatorDisabled) {
    this.operatorDisabled = operatorDisabled;
  }

  public String getOperatorEnabled() {
    return operatorEnabled;
  }

  public void setOperatorEnabled(String operatorEnabled) {
    this.operatorEnabled = operatorEnabled;
  }

  public String getPleaseUseDeopPlayer() {
    return pleaseUseDeopPlayer;
  }

  public void setPleaseUseDeopPlayer(String pleaseUseDeopPlayer) {
    this.pleaseUseDeopPlayer = pleaseUseDeopPlayer;
  }
}

package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class SwitchLocationCommandMessage extends CommandConfig {


  private String notEnoughArguments = "Not enough arguments";

  private String tooManyArguments = "Too many arguments";

  private String notOnline = "Not online";

  private String switchMessage = "You changed position with {player_display_name}"; // target

  private String additionalMessageSwitch = "{player_display_name} changed position with you. Changed by {player_display_name}";

  SwitchLocationCommandMessage() {
    super("switch",
        "swap position with player",
        "/switch <player>", "sst.switch");
  }

  public String getNotEnoughArguments() {
    return notEnoughArguments;
  }

  public void setNotEnoughArguments(String notEnoughArguments) {
    this.notEnoughArguments = notEnoughArguments;
  }

  public String getTooManyArguments() {
    return tooManyArguments;
  }

  public void setTooManyArguments(String tooManyArguments) {
    this.tooManyArguments = tooManyArguments;
  }

  public String getNotOnline() {
    return notOnline;
  }

  public void setNotOnline(String notOnline) {
    this.notOnline = notOnline;
  }

  public String getSwitchMessage() {
    return switchMessage;
  }

  public void setSwitchMessage(String switchMessage) {
    this.switchMessage = switchMessage;
  }

  public String getAdditionalMessageSwitch() {
    return additionalMessageSwitch;
  }

  public void setAdditionalMessageSwitch(String additionalMessageSwitch) {
    this.additionalMessageSwitch = additionalMessageSwitch;
  }
}

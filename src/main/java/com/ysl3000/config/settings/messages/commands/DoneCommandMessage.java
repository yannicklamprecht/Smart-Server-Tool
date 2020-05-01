package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class DoneCommandMessage extends CommandConfig {

  private String modmodeDisabled = "&4modmode disabled";
  private String doneTarget = "&4modmode disabled by {player_name}";
  private String doneSender = "&4modmode disabled for {player_name}";

  public DoneCommandMessage() {
    super("done", "Leave modmode", "/done", "sst.mod");
  }

  public String getModmodeDisabled() {
    return modmodeDisabled;
  }

  public void setModmodeDisabled(String modmodeDisabled) {
    this.modmodeDisabled = modmodeDisabled;
  }

  public String getDoneTarget() {
    return doneTarget;
  }

  public void setDoneTarget(String doneTarget) {
    this.doneTarget = doneTarget;
  }

  public String getDoneSender() {
    return doneSender;
  }

  public void setDoneSender(String doneSender) {
    this.doneSender = doneSender;
  }
}

package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */
public class GodCommandMessage extends CommandConfig {

  private String godmodeMessage = "Godmode is now {god{activated:deactivated}}";

  public GodCommandMessage() {
    super("god", "toggle godmode", "/god",
        "sst.god");
  }

  public String getGodmodeMessage() {
    return godmodeMessage;
  }

  public void setGodmodeMessage(String godmodeMessage) {
    this.godmodeMessage = godmodeMessage;
  }
}

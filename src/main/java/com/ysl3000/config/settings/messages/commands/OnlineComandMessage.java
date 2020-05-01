package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class OnlineComandMessage extends CommandConfig {

  private String onlineMessage = "&7Online ({online_players}/{max_players}): {list_players}";


  OnlineComandMessage() {
    super("online", "lists onlineplayer", "/online",
        "");
  }

  public String getOnlineMessage() {
    return onlineMessage;
  }

  public void setOnlineMessage(String onlineMessage) {
    this.onlineMessage = onlineMessage;
  }
}

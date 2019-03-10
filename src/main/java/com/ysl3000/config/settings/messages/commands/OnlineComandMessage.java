package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */

@Data
public class OnlineComandMessage extends CommandConfig {

  private String onlineMessage = "&7Online ({online_players}/{max_players}): {list_players}";


  OnlineComandMessage(){
  super("online", "lists onlineplayer", "/online",
      "");
  }

}

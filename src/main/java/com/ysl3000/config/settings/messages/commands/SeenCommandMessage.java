package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class SeenCommandMessage extends CommandConfig {

  private String neverSeenBefore = "&aPlayer &6{player_name} &anever seen before!";
  private String playerSeenMessage = "&aPlayer was first seen on: &6{first_played}\n&aand last seen on: &6{last_seen}";

  public SeenCommandMessage() {
    super("seen", "check first/last seen",
        "/seen <player>", "sst.seen");
  }

}

package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class SeenCommandMessage extends CommandConfig {

  private String neverSeenBefore = "&aPlayer &6{player_name} &anever seen before!";
  private String playerSeenMessage = "&aPlayer was first seen on: &6{first_played}\n&aand last seen on: &6{last_seen}";

  public SeenCommandMessage() {
    super("seen", "check first/last seen",
        "/seen <player>", "sst.seen");
  }

  public String getNeverSeenBefore() {
    return neverSeenBefore;
  }

  public void setNeverSeenBefore(String neverSeenBefore) {
    this.neverSeenBefore = neverSeenBefore;
  }

  public String getPlayerSeenMessage() {
    return playerSeenMessage;
  }

  public void setPlayerSeenMessage(String playerSeenMessage) {
    this.playerSeenMessage = playerSeenMessage;
  }
}

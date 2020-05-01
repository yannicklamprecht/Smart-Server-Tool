package com.ysl3000.config.settings.messages.commands;

/**
 * Created by ysl3000
 */

public class SpawnCommandMessage extends CommandConfig {

  private String teleportedToSpawn = "Teleported to Spawn of world &6{world_name}";

  private String playerNotOnline = "Player not online";

  private String teleportOtherToSpawn = "You teleported {player_display_name} to Spawn";

  private String teleportedBySomeoneToSpawn = "You were teleported to spawn by {player_display_name}";

  public SpawnCommandMessage() {
    super("spawn", "Teleport to Spawn", "/spawn",
        "sst.spawn");
  }

  public String getTeleportedToSpawn() {
    return teleportedToSpawn;
  }

  public void setTeleportedToSpawn(String teleportedToSpawn) {
    this.teleportedToSpawn = teleportedToSpawn;
  }

  public String getPlayerNotOnline() {
    return playerNotOnline;
  }

  public void setPlayerNotOnline(String playerNotOnline) {
    this.playerNotOnline = playerNotOnline;
  }

  public String getTeleportOtherToSpawn() {
    return teleportOtherToSpawn;
  }

  public void setTeleportOtherToSpawn(String teleportOtherToSpawn) {
    this.teleportOtherToSpawn = teleportOtherToSpawn;
  }

  public String getTeleportedBySomeoneToSpawn() {
    return teleportedBySomeoneToSpawn;
  }

  public void setTeleportedBySomeoneToSpawn(String teleportedBySomeoneToSpawn) {
    this.teleportedBySomeoneToSpawn = teleportedBySomeoneToSpawn;
  }
}

package com.ysl3000.config.settings.messages.commands;

import lombok.Data;

/**
 * Created by ysl3000
 */
@Data
public class SpawnCommandMessage extends CommandConfig {

  private String teleportedToSpawn = "Teleported to Spawn of world &6{world_name}";

  private String playerNotOnline = "Player not online";

  private String teleportOtherToSpawn = "You teleported {player_display_name} to Spawn";

  private String teleportedBySomeoneToSpawn = "You were teleported to spawn by {player_display_name}";

  public SpawnCommandMessage(){
    super("spawn", "Teleport to Spawn", "/spawn",
        "sst.spawn");
  }

}

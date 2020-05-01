package com.ysl3000.events;

import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.WorldSettings;
import java.util.concurrent.ExecutionException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author yannicklamprecht
 */
public class PlayerStateListener implements Listener {

  private final SmartPlayers smartPlayers;
  private final WorldSettings worldSettings;

  public PlayerStateListener(SmartPlayers smartPlayers, WorldSettings worldSettings) {
    this.smartPlayers = smartPlayers;
    this.worldSettings = worldSettings;
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    if (worldSettings.isXpSave()) {
      event.setDroppedExp(0);
      event.setNewExp(event.getEntity().getPlayer()
          .getTotalExperience());
    }
  }

  @EventHandler
  public void nofood(FoodLevelChangeEvent e) throws ExecutionException {
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (p.getFoodLevel() > e.getFoodLevel()) {
        e.setCancelled(smartPlayers.getPlayerByUUID(p).isGod());
      }

    }
  }


}

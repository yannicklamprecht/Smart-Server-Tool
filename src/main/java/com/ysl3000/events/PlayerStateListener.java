package com.ysl3000.events;

import com.ysl3000.config.settings.WorldSettings;
import com.ysl3000.plugin.SmartPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author yannicklamprecht
 */
public class PlayerStateListener implements Listener {

  private SmartPlayers smartPlayers;
  private WorldSettings worldSettings;

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
  public void nofood(FoodLevelChangeEvent e) {
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (p.getFoodLevel() > e.getFoodLevel()) {
        e.setCancelled(smartPlayers.getPlayerByUUID(p.getUniqueId()).isGod());

      }

    }
  }


}

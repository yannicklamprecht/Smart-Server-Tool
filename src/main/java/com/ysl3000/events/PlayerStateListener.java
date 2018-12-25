package com.ysl3000.events;

import com.ysl3000.plugin.SmartPlayers;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author yannicklamprecht
 */
public class PlayerStateListener implements Listener {
    private JavaPlugin plugin;
    private SmartPlayers smartPlayers;

    public PlayerStateListener(JavaPlugin plugin, SmartPlayers smartPlayers) {
        this.smartPlayers = smartPlayers;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (ConfigFactorizer.createAndReturn(this.plugin).isXpsave()) {
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

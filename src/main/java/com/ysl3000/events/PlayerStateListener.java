/**
 * PlayerStateListener.java
 * <p>
 * Created on , 19:13:41 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 14.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.events;

import com.ysl3000.utils.ConfigFactorizer;
import com.ysl3000.utils.HashMapController;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author yannicklamprecht
 *
 */
public class PlayerStateListener implements Listener {
    private JavaPlugin plugin;

    public PlayerStateListener(JavaPlugin plugin) {
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
                e.setCancelled(HashMapController.getHashMapControler().getSmartPLayers().get(p.getUniqueId()).isGod());

            }

        }
    }


}

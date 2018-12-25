package com.ysl3000.events;

import com.ysl3000.config.SmartSettings;
import com.ysl3000.plugin.SmartPlayers;
import com.ysl3000.utils.Permissions;
import com.ysl3000.plugin.SmartPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerListener implements Listener {
    private SmartPlayers smartPlayers;
    private SmartSettings smartSettings;

    public PlayerListener(SmartPlayers smartPlayers, SmartSettings smartSettings) {
        this.smartPlayers = smartPlayers;
        this.smartSettings = smartSettings;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().teleport(
                event.getPlayer().getBedSpawnLocation() == null ? event
                        .getPlayer().getWorld().getSpawnLocation() : event
                        .getPlayer().getBedSpawnLocation());

    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity etarget = event.getRightClicked();
        if (!(etarget instanceof Player)) {
            return;
        }
        Player target = (Player) etarget;

        if (player.hasPermission(Permissions.playerInfo)) {
            player.sendMessage("Infos of: " + target.getDisplayName());
            player.sendMessage("Foodlevel: " + target.getFoodLevel());
            player.sendMessage("Health: " + "Not available");
            player.sendMessage("Ip: " + target.getAddress());
            player.sendMessage("Op-status: " + target.isOp());
            player.sendMessage("Gamemode: " + target.getGameMode());
            player.sendMessage("XP: " + target.getTotalExperience());
            player.sendMessage("XP-Level: " + target.getLevel());
        }
    }

    @EventHandler
    public void onplayerdive(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode().equals(GameMode.SURVIVAL) && player.getRemainingAir() <= 5) {
            if (player.getInventory().getHelmet() != null) {

                ItemMeta itemMeta = player.getInventory().getHelmet().getItemMeta();

                if (itemMeta instanceof Damageable) {
                    Damageable damageable = (Damageable) itemMeta;

                    int dur = damageable.getDamage();
                    player.setRemainingAir(300);
                    int newdur = (dur + 5);
                    damageable.setDamage(newdur);
                    player.getInventory().getHelmet().setItemMeta(itemMeta);
                    player.sendMessage(ChatColor.GOLD + "Air recharged!!");
                }

            } else {
                player.sendMessage(ChatColor.RED
                        + "you have no helmet to charge your breath");
            }

        }
    }

    @EventHandler
    public void playerteleport(PlayerTeleportEvent event) {
        SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(event.getPlayer().getUniqueId());
        smartPlayer.setLastLocation(event.getFrom());
        smartPlayer.setCurrentLocation(event.getTo());
    }

    @EventHandler
    public void chatHandler(AsyncPlayerChatEvent e) {
        if (!e.getPlayer().hasPermission(Permissions.chat)
                && !smartSettings.isNoPermissionsNeeded()) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("NoPermissions");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(e.getPlayer().getUniqueId());

        e.setCancelled((!(e.getPlayer().hasPermission(Permissions.move)
                || smartSettings.isNoPermissionsNeeded()) && !smartPlayer.isFrozen()));

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if (event.getMessage().startsWith("@")) {

            if (event.getMessage().startsWith("@op ")) {

                event.setMessage(event.getMessage().substring(4));
                removeRecipients(event, ChatColor.RED + "[Need-OP]"
                        + ChatColor.RESET);
                event.getPlayer().sendMessage(ChatColor.GRAY + "Request sent!");
            } else {
                try {
                    String[] cs = event.getMessage().split(" ");
                    Bukkit.getPlayer(
                            event.getMessage().substring(1, cs[0].length()))
                            .sendMessage(privateMessage(event, cs));
                    event.getPlayer().sendMessage(privateMessage(event, cs));
                } catch (Exception e) {
                    event.getPlayer().sendMessage(
                            ChatColor.RED + "Player not found");
                }
                event.setCancelled(true);
            }
        }

    }

    private void removeRecipients(AsyncPlayerChatEvent event,
                                  String channelname) {

        Player[] recipients = event.getRecipients().toArray(new Player[0]);
        event.setFormat(channelname + event.getFormat());

        for (Player recipient : recipients) {
            if (!recipient.isOp()) {
                event.getRecipients().remove(recipient);
            }
        }
    }

    private String privateMessage(AsyncPlayerChatEvent event, String[] cs) {

        return ChatColor.GRAY
                + "(from "
                + event.getPlayer().getDisplayName()
                + ChatColor.GRAY
                + " to "
                + Bukkit.getPlayer(
                event.getMessage().substring(1, cs[0].length()))
                .getDisplayName() + ChatColor.GRAY + "): "
                + ChatColor.RESET
                + event.getMessage().substring(cs[0].length() + 1);
    }

    @EventHandler
    public void nobucketFill(PlayerBucketFillEvent e) {
        e.setCancelled(!(e.getPlayer().hasPermission(Permissions.interact) || smartSettings.isNoPermissionsNeeded()));
    }

    @EventHandler
    public void nobucketEmpty(PlayerBucketEmptyEvent e) {
        e.setCancelled(!(e.getPlayer().hasPermission(Permissions.interact) || smartSettings.isNoPermissionsNeeded()));
    }

}
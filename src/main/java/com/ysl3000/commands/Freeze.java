/**
 * Freeze.java
 * <p>
 * Created on , 19:03:35 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.threads.TimeThread;
import com.ysl3000.utils.HashMapController;
import com.ysl3000.utils.SmartPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Freeze extends CustomCommand {


    public Freeze() {
        super("freeze", "freezes a player",
                "/freeze <player>", "sst.freeze");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player p = (Player) sender;

        if (p.hasPermission(this.getPermission())) {
            if (args.length >= 1 && args.length <= 2) {

                try {
                    long time = Long.parseLong(args[0]);

                    if (args.length == 1) {
                        freezePlayer(p, null, time);

                    } else {

                        Player psender = Bukkit.getPlayer(args[1]);
                        freezePlayer(p, psender, time);
                    }

                } catch (NumberFormatException e) {
                    return true;
                }
            } else {

                p.sendMessage(ChatColor.RED + "Wrong Input");
            }

        } else {
            sender.sendMessage(this.getPermissionMessage());
        }


        return true;
    }

    private void freezePlayer(final Player p, Player sender, long time) {
        String type;

        // todo inject smartplayers
        if (!HashMapController.getHashMapControler()
                .getSmartPLayers().containsKey(p.getUniqueId())) {
            HashMapController.getHashMapControler()
                    .getSmartPLayers()
                    .put(p.getUniqueId(),
                            new SmartPlayer(p));
        }


        if (!HashMapController.getHashMapControler()
                .getSmartPLayers().get(p.getUniqueId()).isFrozen()) {
            type = "Freeze ";

            new TimeThread(time, () -> HashMapController.getHashMapControler().getSmartPLayers()
                    .get(p.getUniqueId()).setFrozen(true), () -> {
                HashMapController.getHashMapControler().getSmartPLayers()
                        .get(p.getUniqueId()).setFrozen(false);
                p.sendMessage("You're now allowed to move");
            });

        } else {
            HashMapController.getHashMapControler().getSmartPLayers()
                    .get(p.getUniqueId()).setFrozen(false);
            type = "Smelt ";
        }
        p.sendMessage(ChatColor.BOLD + type + p.getDisplayName()
                + ChatColor.BOLD + "!" + ChatColor.RESET);
        if (sender != null) {
            sender.sendMessage(type + " player " + p.getCustomName()
                    + " by you ");
        }
    }
}

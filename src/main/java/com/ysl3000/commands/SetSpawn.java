/**
 * SetSpawn.java
 * <p>
 * Created on , 17:35:05 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class SetSpawn extends CustomCommand {


    public SetSpawn() {
        super("setsp", "setspawn", "/setsp",
                "sst.setsp");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) return false;

        if (sender.hasPermission(this.getPermission())) {

            Player player = (Player) sender;

            player.getWorld().setSpawnLocation(
                    player.getLocation().getBlockX(),
                    player.getLocation().getBlockY(),
                    player.getLocation().getBlockZ());

            player.sendMessage("Spawn of " + player.getWorld().getName()
                    + " set");

        }

        return true;
    }
}

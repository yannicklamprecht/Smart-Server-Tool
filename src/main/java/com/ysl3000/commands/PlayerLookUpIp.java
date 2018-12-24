/**
 * PlayerLookUpIp.java
 * <p>
 * Created on , 17:27:59 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class PlayerLookUpIp extends CustomCommand {


    public PlayerLookUpIp() {
        super("/ip", "get ip of player",
                "//ip <player>", "sst.ip");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (player.hasPermission(this.getPermission())) {
            if (args.length != 1) return false;
            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {

                player.sendMessage("PLAYER " + target + " isn't found");
                return true;
            }
            player.sendMessage("Ip of " + ChatColor.GOLD + target.getName()
                    + ChatColor.WHITE + " is " + ChatColor.YELLOW
                    + target.getAddress());

        } else {
            sender.sendMessage(this.getPermissionMessage());
        }
        return true;
    }
}

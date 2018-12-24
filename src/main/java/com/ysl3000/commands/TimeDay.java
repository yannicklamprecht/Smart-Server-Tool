/**
 * TimeDay.java
 * <p>
 * Created on , 14:45:53 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class TimeDay extends CustomCommand {


    public TimeDay() {
        super("td", "Set time to day", "/td", "sst.time");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (player.hasPermission(this.getPermission())) {
            player.getWorld().setTime(0);
            player.sendMessage("Time set to " + ChatColor.GOLD
                    + "Day");
        } else {
            sender.sendMessage(this.getPermissionMessage());
        }

        return true;
    }
}

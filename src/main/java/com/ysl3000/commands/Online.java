/**
 * Online.java
 * <p>
 * Created on , 18:50:44 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 *
 */
public class Online extends CustomCommand {

    public Online() {
        super("online", "lists onlineplayer", "/online",
                "");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) return false;
        ((Player) sender).sendMessage(ChatColor.GRAY + "Online ("
                + Bukkit.getServer().getOnlinePlayers().size() + "/"
                + Bukkit.getMaxPlayers() + "): " + Utility.listPlayers());

        return true;
    }
}

/**
 * KillMe.java
 * <p>
 * Created on , 15:14:51 by @author Yannick Lamprecht
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
 */
public class KillMe extends CustomCommand {

    public KillMe() {

        super("km", "kill's yourself", "/km",
                "sst.km");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (player.hasPermission(this.getPermission())) {
            player.setHealth(0.0);

        } else {
            sender.sendMessage(this.getPermissionMessage());
        }

        return true;
    }
}

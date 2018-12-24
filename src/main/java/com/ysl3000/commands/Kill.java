/**
 * Kill.java
 * <p>
 * Created on , 15:41:41 by @author Yannick Lamprecht
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
public class Kill extends CustomCommand {


    public Kill() {
        super("Kill", "kill player", "/kill <player>", "sst.kill");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player))
            return false;
        Player player = (Player) sender;

        if (player.hasPermission(this.getPermission())) {

            if (args.length == 0) {
                player.sendMessage("Need more arguments");
            } else if (args.length == 1) {

                Player target = sender.getServer().getPlayer(
                        args[0]);

                if (target == null) {
                    player.sendMessage("Player not found");
                    return true;
                }
                target.setHealth(0.0);
                player.sendMessage(target.getDisplayName()
                        + " killed successfully");
            }
        } else {
            sender.sendMessage(this.getPermissionMessage());
        }
        return true;
    }
}

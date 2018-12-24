/**
 * Heal.java
 * <p>
 * Created on , 15:46:53 by @author Yannick Lamprecht
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
public class Heal extends CustomCommand {


    public Heal() {
        super("heal", "heal someone", "/heal <player>", "sst.heal");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player))
            return false;

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("You need one argument");
            return true;
        }
        Player target = sender.getServer().getPlayer(args[0]);

        if (player.hasPermission(this.getPermission())) {

            if (target == null) {

                sender.sendMessage("Player not found");
                return true;

            }
            target.setHealth(20.0);
            target.setFoodLevel(20);
            target.sendMessage(ChatColor.GREEN
                    + "You have been healed by "
                    + ChatColor.DARK_PURPLE
                    + player.getDisplayName());

            player.sendMessage(ChatColor.GREEN + "You healed "
                    + ChatColor.DARK_PURPLE + target.getName());
        } else {
            sender.sendMessage(this.getPermissionMessage());
        }

        return true;
    }
}

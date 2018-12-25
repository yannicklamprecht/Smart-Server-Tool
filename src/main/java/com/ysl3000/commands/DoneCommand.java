/**
 * DoneCommand.java
 * <p>
 * Created on , 14:27:17 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.plugin.SmartPlayers;
import com.ysl3000.utils.SmartPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class DoneCommand extends CustomCommand {


    private SmartPlayers smartPlayers;


    public DoneCommand(SmartPlayers smartPlayers) {
        super("done", "Leave modmode", "/done", "sst.mod");
        this.smartPlayers = smartPlayers;
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (player.hasPermission(this.getPermission())) {
            if (args.length == 0) {

                done(player, null); // todo refactor do not pass nullable instead overload method

            } else if (args.length == 1) {
                Player target = player.getServer().getPlayer(
                        args[0]);
                done(target, player);
            }

        }

        return true;
    }

    private void done(Player target, Player player) {

        SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(target.getUniqueId());

        if (smartPlayer.isMod()) {
            target.setGameMode(GameMode.SURVIVAL);
            target.getInventory().clear();
            target.getInventory().setContents(smartPlayer.getInventory());
            target.teleport(smartPlayer.getModLocation());
            target.sendMessage((ChatColor.RED + "modmode disabled"));
            smartPlayer.setMod(false);

            if (player != null) {
                player.sendMessage((ChatColor.RED
                        + "modmode disabled for " + target
                        .getName()));
            }

        }

    }
}

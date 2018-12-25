package com.ysl3000.commands;


import com.ysl3000.plugin.SmartPlayers;
import com.ysl3000.threads.TimeThread;
import com.ysl3000.utils.SmartPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Freeze extends CustomCommand {

    private SmartPlayers smartPlayers;

    public Freeze(SmartPlayers smartPlayers) {
        super("freeze", "freezes a player",
                "/freeze <player>", "sst.freeze");
        this.smartPlayers = smartPlayers;
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
                        freezePlayer(p, time);

                    } else {
                        Player target = Bukkit.getPlayer(args[1]);
                        freezePlayer(target, p, time);
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

    private void freezePlayer(Player p, Player sender, long time) {
        freezePlayer(p,time);
        // todo send messages to sender

    }

    private void freezePlayer(final Player p, long time) {

        // todo send messages to target
        SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(p.getUniqueId());

        if (!smartPlayer.isFrozen()) {
            new TimeThread(time, () -> smartPlayer.setFrozen(true), () -> {
                smartPlayer.setFrozen(false);
                p.sendMessage("You're now allowed to move");
            });

        } else {
            smartPlayer.setFrozen(false);
        }
    }
}

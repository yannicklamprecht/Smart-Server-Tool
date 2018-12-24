
package com.ysl3000.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class AdminCommand extends CustomCommand {


    public AdminCommand() {
        super("/admin", "Toggle op", "//admin", "sst.admin");
    }

    public boolean execute(CommandSender sender, String s, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (!player.isOp()
                    && player
                    .hasPermission(this.getPermission())) {

                player.setOp(true);

                player.sendMessage((ChatColor.GREEN + "Op enabled"));

            } else if (player.isOp()
                    && player
                    .hasPermission(this.getPermission())) {

                player.setOp(false);

                player.sendMessage((ChatColor.RED + "Op disabled"));
            }
        } else if (args.length == 1) {
            player.sendMessage("please use /deop <player>");
        }

        return true;
    }
}

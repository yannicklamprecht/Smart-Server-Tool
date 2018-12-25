package com.ysl3000.commands;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class ServerInfo extends CustomCommand {


    public ServerInfo() {
        super("serverinfo", "get Serverinfo",
                "/serverinfo", "sst.info");
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if (player.hasPermission(this.getPermission())) {
            double total = (((Runtime.getRuntime().totalMemory()) / 1024.0) / 1024);
            double max = (((Runtime.getRuntime().maxMemory()) / 1024.0) / 1024);
            int cpu = Runtime.getRuntime().availableProcessors();

            sender.sendMessage("Current memoryuse "
                    + total
                    + "/"
                    + max
                    + " mb\nThis host has: "
                    + cpu
                    + " cpu's\nThe seed is : "
                    + Bukkit.getWorld(player.getWorld().getName()).getSeed()
                    + "\nServer is in "
                    + (Bukkit.getOnlineMode() ? ChatColor.GREEN + "online"
                    : ChatColor.RED + "offline") + ChatColor.RESET
                    + "-mode\n" + ChatColor.GRAY + "Online ("
                    + Bukkit.getServer().getOnlinePlayers().size() + "/"
                    + Bukkit.getMaxPlayers() + ")");
        }
        return true;
    }
}

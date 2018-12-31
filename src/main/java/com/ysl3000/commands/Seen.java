package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Seen extends CustomCommand {


  private final Utility utility;

  public Seen(CommandConfig commandConfig,Utility utility) {
    super(commandConfig);
    this.utility = utility;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (!sender.hasPermission(this.getPermission())) {
      sender.sendMessage(this.getPermissionMessage());
      return false;
    }

    Player player = (Player) sender;

    if (args.length == 0) {
      player.sendMessage("Use /seen <player>");
    } else if (args.length == 1) {
      OfflinePlayer op = Bukkit.getServer().getOfflinePlayer(args[0]);

      if (op.hasPlayedBefore()) {
        player.sendMessage(ChatColor.GREEN
            + "Player was first seen on: "
            + ChatColor.GOLD
            + utility.getTime(op.getFirstPlayed(),
            "MMM dd yyyy HH:mm")
            + "\n"
            + ChatColor.GREEN
            + "and last seen on: "
            + ChatColor.GOLD
            + utility.getTime(op.getLastPlayed(),
            "MMM dd yyyy HH:mm"));
      } else {
        player.sendMessage(ChatColor.GREEN + "Player " + ChatColor.GOLD
            + op.getName() + ChatColor.GREEN
            + " never seen before!");
      }
    } else {
      player.sendMessage(ChatColor.RED + "To much arguments");
    }

    return true;
  }
}

package com.ysl3000.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Sun extends CustomCommand {

  public Sun() {
    super("sun", "Set sun", "/sun", "sst.weather");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {

      player.getWorld().setThundering(false);
      player.getWorld().setStorm(false);
      player.sendMessage("Weather set to " + ChatColor.GOLD
          + "Sun");
    }

    return true;
  }
}

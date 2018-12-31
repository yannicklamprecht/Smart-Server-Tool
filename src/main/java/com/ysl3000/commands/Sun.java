package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Sun extends CustomCommand {

  public Sun(CommandConfig commandConfig) {
    super(commandConfig);
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
      // todo inject messages
      player.sendMessage("Weather set to " + ChatColor.GOLD
          + "Sun");
    }

    return true;
  }
}

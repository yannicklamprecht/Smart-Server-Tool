package com.ysl3000.commands;

import com.ysl3000.config.settings.CommandConfig;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public abstract class AbstractTimeCommand extends CustomCommand {

  public AbstractTimeCommand(CommandConfig commandConfig) {
    super(commandConfig);
  }

  public AbstractTimeCommand(String name, String description, String usageMessage,
      List<String> aliases, String permission) {
    super(name, description, usageMessage, aliases, permission);
  }

  void displayNotifyMessage(Player player, long time, String type) {
    if (player.hasPermission(this.getPermission())) {
      player.getWorld().setTime(time);
      player.sendMessage("Time set to " + ChatColor.GOLD + type);
    } else {
      player.sendMessage(this.getPermissionMessage());
    }
  }
}

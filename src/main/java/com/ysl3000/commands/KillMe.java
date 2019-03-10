package com.ysl3000.commands;


import com.ysl3000.config.settings.messages.commands.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class KillMe extends CustomCommand {

  KillMe(CommandConfig commandConfig) {
    super(commandConfig);
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {
      player.setHealth(0.0);

    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

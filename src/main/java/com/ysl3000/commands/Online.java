package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import com.ysl3000.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class Online extends CustomCommand {

  private Utility utility;

  public Online(CommandConfig commandConfig,Utility utility) {
    super(commandConfig);
    this.utility = utility;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }
    sender.sendMessage(ChatColor.GRAY + "Online ("
        + Bukkit.getServer().getOnlinePlayers().size() + "/"
        + Bukkit.getMaxPlayers() + "): " + utility.listPlayers());

    return true;
  }
}

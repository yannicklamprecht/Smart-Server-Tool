/**
 * God.java
 * <p>
 * Created on , 18:41:01 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
public class God extends CustomCommand {

  private SmartPlayers smartPlayers;


  public God(SmartPlayers smartPlayers) {
    super("god", "Toggle godmode", "/god", "sst.god");
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] strings) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player p = (Player) sender;
    if (p.hasPermission(this.getPermission())) {

      SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(p.getUniqueId());
      smartPlayer.setGod(!smartPlayer.isGod());

      p.sendMessage("Godmode set to " + (smartPlayer.isGod() ? "True" : "False"));

    } else {
      sender.sendMessage(this.getPermissionMessage());
    }

    return true;
  }
}

/**
 * SurvivalGamemode.java
 * <p>
 * Created on , 12:06:41 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 *
 */
public class SurvivalGamemode extends CustomCommand {


  public SurvivalGamemode() {
    super("gms", "Set Survival", "/gms",
        "sst.gamemode");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {

      if (args.length == 0) {

        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage("Enter " + ChatColor.GOLD
            + player.getGameMode());

      } else if (args.length == 1
          && player.hasPermission(Permissions.gamemodeOther)) {

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {

          sender.sendMessage("PLAYER " + args[0] + " isn't found");
          return true;
        }

        target.setGameMode(GameMode.SURVIVAL);

        target.sendMessage(ChatColor.GREEN + "You entered "
            + target.getGameMode() + " set by "
            + ChatColor.DARK_PURPLE + sender.getName());

        sender.sendMessage(ChatColor.GREEN + "Gamemode set to  "
            + target.getGameMode() + " " + ChatColor.DARK_PURPLE +

            target.getName());
      }

    }

    return true;
  }
}

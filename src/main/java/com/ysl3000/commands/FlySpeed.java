/**
 * FlySpeed.java
 * <p>
 * Created on , 14:35:14 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class FlySpeed extends CustomCommand {

  public FlySpeed(CommandConfig commandConfig) {
    super(commandConfig);

  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return true;
    }

    if (args.length != 1) {
      sender.sendMessage(this.getUsage());
      return false;
    }
    Player p = (Player) sender;

    try {
      Float.parseFloat(args[0]);
    } catch (NumberFormatException e) {
      return false;
    }
    if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
      p.setFlySpeed(Float.parseFloat(args[0]));

      p.sendMessage("Flyspeed set to " + p.getFlySpeed());
    } else {
      p.sendMessage("Speed has to be between 0.1 and 1.0");
    }
    return true;
  }
}

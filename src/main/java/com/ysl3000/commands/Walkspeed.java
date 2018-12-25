/**
 * Walkspeed.java
 * <p>
 * Created on , 18:37:55 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht This program comes with
 * ABSOLUTELY NO WARRANTY; This is free software, and you are welcome to redistribute it under
 * certain conditions;
 */
package com.ysl3000.commands;


import java.util.regex.Pattern;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Walkspeed extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");


  public Walkspeed() {
    super("ws", "set walkspeed",
        "/ws <amount/0.1-1.0>", "");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    if (args.length != 1) {
      sender.sendMessage(this.getUsage());
      return false;
    }
    Player p = (Player) sender;

    if (NUMBER.matcher(args[0]).matches()) {
      if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
        p.setWalkSpeed(Float.parseFloat(args[0]));
        p.sendMessage("Walkspeed set to " + p.getWalkSpeed());
      } else {
        p.sendMessage("Speed has to be between 0.1 and 1.0");
      }
    }

    return true;
  }
}

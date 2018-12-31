package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import java.util.regex.Pattern;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 */
public class Walkspeed extends CustomCommand {

  private static final Pattern NUMBER = Pattern.compile("\\d");


  public Walkspeed(CommandConfig commandConfig) {
    super(commandConfig);
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
        // todo configurable
        p.sendMessage("Walkspeed set to " + p.getWalkSpeed());
      } else {
        // todo configurable
        p.sendMessage("Speed has to be between 0.1 and 1.0");
      }
    }

    return true;
  }
}

package com.ysl3000.commands;


import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author yannicklamprecht
 *
 */
public class SwitchLocation extends CustomCommand {


  public SwitchLocation(CommandConfig commandConfig) {
    super(commandConfig);
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {
      if (args.length == 0) {
        player.sendMessage("Not enough arguments");
      } else if (args.length == 1) {
        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
          player.sendMessage("Not online");
          return true;
        }

        Location loca = player.getLocation();
        player.teleport(target.getLocation());
        target.teleport(loca);

        if (target.canSee(player)) {

          player.sendMessage("You changed position with "
              + target.getDisplayName());
          target.sendMessage(player.getDisplayName()
              + " changed position with you. Changed by "
              + player.getDisplayName());
        }
      } else {
        player.sendMessage("to many arguments");
      }
    }
    return true;
  }
}

package com.ysl3000.commands;

import com.ysl3000.utils.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
// todo multilang support and some cleanup
public class FlyMode extends CustomCommand {


  public FlyMode() {
    super("fly", "toggle fly", "/fly <player>", "sst.fly");
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }
    Player player = (Player) sender;

    if (player.hasPermission(this.getPermission())) {

      if (args.length == 0) {

        if (!player.getAllowFlight()
            && !player.isFlying()) {

          player.setAllowFlight(true);
          player.setFlying(true);
          player.sendMessage("You can now fly ");
        } else if (player.getAllowFlight()
            && !player.isFlying()) {
          player.setFlying(false);
          player.setAllowFlight(false);
          player.sendMessage("Fly is now disabled");
        }
      } else if (args.length == 1) {

        Player target = Bukkit.getPlayer(args[0]);

        if (player.hasPermission(Permissions.FLY_OTHER)) {

          if (!target.getAllowFlight() && !target.isFlying()) {

            target.setAllowFlight(true);
            target.setFlying(true);
            sender.sendMessage("Set fly on for " + target.getDisplayName());
            target.sendMessage("You can now fly! Allowed by " + ((Player) sender).getDisplayName());

          } else if (target.getAllowFlight() && !target.isFlying()) {

            target.setFlying(false);
            target.setAllowFlight(false);
            sender.sendMessage("Set fly off for " + target.getDisplayName());
            target.sendMessage(
                "Until now you have to walk on feet! Disallowed by " + ((Player) sender)
                    .getDisplayName());
          } else if (target.getAllowFlight()
              && target.isFlying()) {

            sender.sendMessage(target.getDisplayName()
                + " is flying! Only if player is on earth you can disble that!");

          }
        } else {
          sender.sendMessage("No permission for flying others");
        }

      }

    } else {
      sender.sendMessage(this.getPermissionMessage());
    }
    return true;
  }
}

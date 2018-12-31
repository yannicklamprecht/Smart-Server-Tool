package com.ysl3000.commands;


import com.ysl3000.SmartPlayer;
import com.ysl3000.SmartPlayers;
import com.ysl3000.config.settings.CommandConfig;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author yannicklamprecht
 */
// todo add multilanguage support
public class DoneCommand extends CustomCommand {


  private SmartPlayers smartPlayers;


  public DoneCommand(CommandConfig commandConfig,SmartPlayers smartPlayers) {
    super(commandConfig);
    this.smartPlayers = smartPlayers;
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (!(sender instanceof Player)) {
      return false;
    }

    Player player = (Player) sender;
    if (player.hasPermission(this.getPermission())) {
      if (args.length == 0) {
        done(player);
        player.sendMessage((ChatColor.RED + "modmode disabled"));
      } else if (args.length == 1) {
        Player target = player.getServer().getPlayer(args[0]);
        done(target);
        target.sendMessage(ChatColor.RED + "modmode disabled by " + player.getName());
        player.sendMessage(ChatColor.RED + "modmode disabled for " + target.getName());
      }

    }

    return true;
  }


  private void done(Player target) {
    SmartPlayer smartPlayer = smartPlayers.getPlayerByUUID(target.getUniqueId());
    if (smartPlayer.isMod()) {
      target.setGameMode(GameMode.SURVIVAL);
      target.getInventory().clear();
      target.getInventory().setContents(smartPlayer.getInventory());
      target.teleport(smartPlayer.getModLocation());
      smartPlayer.setMod(false);
    }
  }
}

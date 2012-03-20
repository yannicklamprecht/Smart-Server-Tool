package com.github.ysl3000;

import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Top {

	public static boolean toggleop(Command cmd, CommandSender sender, String[] args)
			throws IOException {
		
		Player player = (Player)sender;

		if (cmd.getName().equalsIgnoreCase("/mod")) {

			if(args.length == 0){
				
				if (!sender.isOp() && sender.hasPermission("sst.admin")) {
					sender.setOp(true);
					sender.sendMessage((ChatColor.GREEN + "Op enabled"));
				} else if (sender.isOp() && sender.hasPermission("sst.admin")) {
					sender.setOp(false);
					sender.sendMessage((ChatColor.RED + "Op disabled"));
				}
			}else if (args.length == 1){
				
				Player target = player.getServer().getPlayer(args[0]);
				if ( !target.isOp()&&sender.hasPermission("sst.admin")) {
					target.setOp(true);
					target.setNoDamageTicks(-1);
					sender.sendMessage((ChatColor.GREEN + "Op enabled for "+target.getName()));
					target.sendMessage((ChatColor.GREEN + "Op enabled"));
				} else if ( target.isOp()&&sender.hasPermission("sst.admin")) {
					target.setOp(false);
					target.setNoDamageTicks(1);
					sender.sendMessage((ChatColor.RED + "Op disabled for "+target.getName()));
					target.sendMessage((ChatColor.RED + "Op disabled"));
				}
			}
			
			
			

		}
		if(cmd.getName().equalsIgnoreCase("/admin")){
			
			if(!sender.isOp()&& sender.hasPermission("sst.admin") && !player.getGameMode().equals(GameMode.CREATIVE)){
				
				player.setOp(true);
				player.setGameMode(GameMode.CREATIVE);
				sender.sendMessage((ChatColor.GREEN + "Modmode enabled"));
			}else if (sender.isOp()&& sender.hasPermission("sst.admin") && player.getGameMode().equals(GameMode.CREATIVE)){
				
				player.setOp(false);
				player.setGameMode(GameMode.SURVIVAL);
				sender.sendMessage((ChatColor.RED + "Modmode disabled"));
			}
			
		}
		return false;
		

	}
}

package com.github.ysl3000;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class Spec {

	
	public static void spec(Player player, String command, String[] args,
			Command cmd){
		
	
		if(command.equalsIgnoreCase("inv")){
			
			if(args.length == 1){
				Player target = player.getServer().getPlayer(args[0]);
				player.openInventory(target.getInventory());
			}else{
				player.openInventory(player.getInventory());
			}
			
		}
		
	}
}

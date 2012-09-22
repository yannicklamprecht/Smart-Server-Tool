package com.github.ysl3000;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class EntityListener {

	
	
	public static void removeEntity(CommandSender sender, String command,
			String[] args, Command cmd){
		
		if(command.equalsIgnoreCase("al") && sender.hasPermission("sst.al")){
			
			Player player = (Player) sender;
			int count = 0;
			for(Entity e : player.getWorld().getEntities()){
				
				
				if(e.getType().equals(EntityType.WOLF)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.BLAZE)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.CAVE_SPIDER)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.CREEPER)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.ENDER_DRAGON)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.ENDERMAN)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.GHAST)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.SKELETON)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.SPIDER)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.ZOMBIE)){
					e.remove();
					count++;
				}
				if(e.getType().equals(EntityType.SLIME)){
					e.remove();
					count++;
				}
			}
			
			//if(!e.getType().equals(EntityType.PLAYER)){
				//e.remove();
			//}
			
			player.sendMessage(ChatColor.ITALIC +""+count + " Entities removed");
		}
		
		
	}
}

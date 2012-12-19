package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class EntityManager {

	private static int count = 0;
	private static String Type;

	public static void removeEntity(CommandSender sender, String command,
			String[] args, Command cmd) {

		if (Commands.getAL(command) && Permission.hasclearEntities((Player)sender)) {

			Player player = (Player) sender;
			setCount(0);

			if (args.length != 1) {
				player.sendMessage("No arguements");
			} else {
				if (args[0].equalsIgnoreCase("-a")) {

					for (Entity e : player.getWorld().getEntities()) {

						EntityManager.removeMonster(e);

					}
					EntityManager.setType("monster");

				} else if (args[0].equalsIgnoreCase("-p")) {
					for (Entity e : player.getWorld().getEntities()) {
						EntityManager.removerMobs(e);
					}
					EntityManager.setType("passive mob");
				} else if (args[0].equalsIgnoreCase("-i")) {

					for (Entity e : player.getWorld().getEntities()) {
						EntityManager.removeItems(e);
					}
					EntityManager.setType("Items");
				} else if (args[0].equalsIgnoreCase("+a")) {
					for (Entity e : player.getWorld().getEntities()) {

						if(120 <= player.getWorld().getEntities().size()){
							e.remove();
							EntityManager.setCount(EntityManager.getCount() + 1);
							
						}
					}
					EntityManager.setType("Entities");
				}

				//

				player.sendMessage(ChatColor.ITALIC + ""
						+ EntityManager.getCount() + " "
						+ EntityManager.getType() + " removed");
			}
		} else if (Commands.getAL(command)
				&& !Permission.hasclearEntities((Player)sender)) {
			((Player) sender).sendMessage(SmartServerTool.noperms);
		}

	}

	public static void removeMonster(Entity e) {

		if (e.getType().equals(EntityType.WOLF)) {
			e.remove();
			setCount(getCount() + 1);

		}
		if (e.getType().equals(EntityType.BLAZE)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.CAVE_SPIDER)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.CREEPER)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.ENDER_DRAGON)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.ENDERMAN)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.GHAST)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.SKELETON)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.SPIDER)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.ZOMBIE)) {
			e.remove();
			setCount(getCount() + 1);
		}
		if (e.getType().equals(EntityType.SLIME)) {
			e.remove();
			setCount(getCount() + 1);
		}

	}

	public static void removerMobs(Entity e) {

		if (e.getType().equals(EntityType.CHICKEN)) {
			e.remove();
			setCount(getCount() + 1);

		} else if (e.getType().equals(EntityType.COW)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.OCELOT)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.MUSHROOM_COW)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.PIG)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.SHEEP)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.SNOWMAN)) {
			e.remove();
			setCount(getCount() + 1);
		} else if (e.getType().equals(EntityType.SQUID)) {

		} else if (e.getType().equals(EntityType.WOLF)) {
			e.remove();
			setCount(getCount() + 1);
		}

	}

	public static void removeItems(Entity e) {

		if (e instanceof Item) {
			e.remove();
			EntityManager.setCount(EntityManager.getCount() + 1);
		}

	}

	public static void setCount(int count) {
		EntityManager.count = count;
	}

	public static int getCount() {
		return count;
	}

	public static void setType(String type) {
		Type = type;
	}

	public static String getType() {
		return EntityManager.Type;
	}
}

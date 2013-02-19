package com.github.ysl3000;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
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

		if (Commands.getAL(command)
				&& Permission.hasclearEntities((Player) sender)) {

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
						EntityManager.removeMobs(e);
					}
					EntityManager.setType("passive mob");
				} else if (args[0].equalsIgnoreCase("-i")) {

					for (Entity e : player.getWorld().getEntities()) {
						EntityManager.removeItems(e);
					}
					EntityManager.setType("Items");
				}

				player.sendMessage(ChatColor.ITALIC + ""
						+ EntityManager.getCount() + " "
						+ EntityManager.getType() + " removed");
			}
		} else if (Commands.getAL(command)
				&& !Permission.hasclearEntities((Player) sender)) {
			((Player) sender).sendMessage(SmartServerTool.noperms);
		}

	}

	public static void removeMonster(Entity e) {
		if (e.getType().equals(EntityType.WOLF)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.BLAZE)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.CAVE_SPIDER)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.CREEPER)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.ENDER_DRAGON)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.ENDERMAN)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.GHAST)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.SKELETON)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.SPIDER)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.ZOMBIE)) {
			removeEntitiesWithStyle(e);
		}
		if (e.getType().equals(EntityType.SLIME)) {
			removeEntitiesWithStyle(e);
		}
	}

	public static void removeMobs(Entity e) {

		if (e.getType().equals(EntityType.CHICKEN)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.COW)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.OCELOT)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.MUSHROOM_COW)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.PIG)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.SHEEP)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.SNOWMAN)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.SQUID)) {
			removeEntitiesWithStyle(e);
		} else if (e.getType().equals(EntityType.WOLF)) {
			removeEntitiesWithStyle(e);
		}

	}

	public static void removeItems(Entity e) {
		if (e instanceof Item) {
			removeEntitiesWithStyle(e);
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

	public static void removeEntitiesWithStyle(Entity e) {
		e.remove();
		e.playEffect(EntityEffect.DEATH);
		setCount(getCount() + 1);
	}

}

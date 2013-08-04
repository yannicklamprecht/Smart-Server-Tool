package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class EntityManager implements CommandExecutor {

	private static int count = 0;
	private static String Type;

	public void removeMonster(Entity e) {
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

	public void removeMobs(Entity e) {

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

	public void removeItems(Entity e) {
		if (e instanceof Item) {
			removeEntitiesWithStyle(e);
		}
	}

	public void setCount(int count) {
		EntityManager.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getType() {
		return EntityManager.Type;
	}

	public void removeEntitiesWithStyle(Entity e) {
		e.remove();
		e.playEffect(EntityEffect.DEATH);
		setCount(getCount() + 1);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission())) {
			player.sendMessage(SmartServerTool.noperms);
			return true;
		}

		setCount(0);

		if (args.length != 1) {
			player.sendMessage("No arguements");
		} else {
			if (args[0].equalsIgnoreCase("-a")) {

				for (Entity e : player.getWorld().getEntities()) {
					removeMonster(e);
				}
				setType("monster");

			} else if (args[0].equalsIgnoreCase("-p")) {
				for (Entity e : player.getWorld().getEntities()) {
					removeMobs(e);
				}
				setType("passive mob");
			} else if (args[0].equalsIgnoreCase("-i")) {

				for (Entity e : player.getWorld().getEntities()) {
					removeItems(e);
				}
				setType("Items");
			}

			player.sendMessage(ChatColor.ITALIC + "" + getCount() + " "
					+ getType() + " removed");

		}

		return true;
	}
}

package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.ysl3000.SmartServerTool;

public class ModCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission()))
			return false;

		if (args.length == 0) {

			setMod(player, null);

		} else if (args.length == 1) {

			Player target = player.getServer().getPlayer(args[0]);

			setMod(target, player);
		}

		return true;
	}

	public void setMod(Player target, Player player) {

		if (!(SmartServerTool.getHSP().isInventoryIn(target)
				|| SmartServerTool.getHSP().hasLastLocation(target) || SmartServerTool
				.getHSP().getIsMod(target))) {

			if (target.getInventory().getContents().length == 0) {

				target.getInventory().addItem(new ItemStack(0, 4));
			}

			SmartServerTool.getHSP().setInventory(target);

			SmartServerTool.getHSP().setLastLocation(target,
					target.getLocation());
			target.getInventory().clear();
			target.setOp(true);
			target.setGameMode(GameMode.CREATIVE);
			target.sendMessage((ChatColor.GREEN + "Modmode enabled"));
			SmartServerTool.getHSP().setIsMOD(target);

			if (player != null) {
				player.sendMessage((ChatColor.GREEN + "modmode enabled for "
						+ ChatColor.GOLD + target.getName()));
			}

		}

	}

}

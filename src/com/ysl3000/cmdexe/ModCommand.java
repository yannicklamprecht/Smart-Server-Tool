package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.ysl3000.utils.SmartController;

public class ModCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission()))
			return false;

		if (args.length == 0) {

			setModMode(player, null);

		} else if (args.length == 1) {

			Player target = player.getServer().getPlayer(args[0]);

			setModMode(target, player);
		}

		return true;
	}

	private void setModMode(Player target, Player player) {

		if (!SmartController.getSmartControler().getHashmaps()
				.getSmartPLayers().get(target).isMod()) {

			if (target.getInventory().getContents().length == 0) {

				target.getInventory().addItem(new ItemStack(0, 4));
			}

			SmartController.getSmartControler().getHashmaps().getSmartPLayers()
					.get(target)
					.setInventory(target.getInventory().getContents());

			SmartController.getSmartControler().getHashmaps().getSmartPLayers()
					.get(target).setModLocation(target.getLocation());
			;
			target.getInventory().clear();
			target.setOp(true);
			target.setGameMode(GameMode.CREATIVE);
			target.sendMessage((ChatColor.GREEN + "Modmode enabled"));

			SmartController.getSmartControler().getHashmaps().getSmartPLayers()
					.get(target).setMod(true);

			if (player != null) {
				player.sendMessage((ChatColor.GREEN + "modmode enabled for "
						+ ChatColor.GOLD + target.getName()));
			}

		}
	}

}

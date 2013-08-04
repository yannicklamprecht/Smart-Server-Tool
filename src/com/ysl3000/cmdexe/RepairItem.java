package com.ysl3000.cmdexe;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class RepairItem implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Player player = (Player) sender;

		if (!player.hasPermission(cmd.getPermission())) {
			player.sendMessage(SmartServerTool.noperms);
			return true;
		}

		Map<Enchantment, Integer> ench = player.getItemInHand()
				.getEnchantments();

		short du = 0;
		player.getItemInHand().setDurability(du);
		player.getItemInHand().addEnchantments(ench);

		return false;
	}

}

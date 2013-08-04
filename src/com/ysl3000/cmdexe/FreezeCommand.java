package com.ysl3000.cmdexe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.SmartServerTool;

public class FreezeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player p = (Player) sender;

		if (p.hasPermission(cmd.getPermission())) {
			if (args.length == 1) {
				String type;
				Player target = Bukkit.getPlayer(args[0]);
				if (!SmartServerTool.getHSP().isFrozen(target)) {
					type = "Freeze ";

					new TimeThread(3000, target);

				} else {
					SmartServerTool.getHSP().removeFrozen(target);
					type = "Smelt ";
				}
				p.sendMessage(ChatColor.BOLD + type + target.getDisplayName()
						+ ChatColor.BOLD + "!" + ChatColor.RESET);
			} else {
				p.sendMessage(ChatColor.RED + "Wrong Input");
			}
		}

		return true;
	}

	private class TimeThread extends Thread {

		private long time;
		private Player p;

		public TimeThread(int time, Player p) {
			this.setTime(time);
			this.p = p;
			this.start();
		}

		public Player getPlayer() {
			return p;
		}

		public void setTime(long time) {
			this.time = time;
		}

		public void run() {

			SmartServerTool.getHSP().setFrozen(this.getPlayer());
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			SmartServerTool.getHSP().removeFrozen(this.getPlayer());
			p.sendMessage("You're now allowed to move");
		}

	}

}

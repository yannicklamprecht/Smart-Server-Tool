package com.ysl3000.cmdexe;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.utils.SmartController;

public class FreezeCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {

		Player p = (Player) sender;

		if (p.hasPermission(cmd.getPermission())) {
			if (args.length == 1) {
				String type;
				if (!SmartController.getSmartControler().getHashmaps()
						.getSmartPLayers().get(p).isFrozen()) {
					type = "Freeze ";

					new TimeThread(3000, p);

				} else {
					SmartController.getSmartControler().getHashmaps()
							.getSmartPLayers().get(p).setFrozen(false);
					type = "Smelt ";
				}
				p.sendMessage(ChatColor.BOLD + type + p.getDisplayName()
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

			SmartController.getSmartControler().getHashmaps().getSmartPLayers()
					.get(this.getPlayer()).setFrozen(true);
			try {
				Thread.sleep(this.time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			SmartController.getSmartControler().getHashmaps().getSmartPLayers()
					.get(this.getPlayer()).setFrozen(false);
			p.sendMessage("You're now allowed to move");
		}

	}

}

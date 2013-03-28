package com.github.ysl3000.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ysl3000.ChannelChat;
import com.github.ysl3000.EntityManager;
import com.github.ysl3000.Gm;
import com.github.ysl3000.Health;
import com.github.ysl3000.HideP;
import com.github.ysl3000.Inviter;
import com.github.ysl3000.KickManager;
import com.github.ysl3000.Questioner;
import com.github.ysl3000.Time;
import com.github.ysl3000.Item.ItemMan;
import com.github.ysl3000.Location.SSTH;
import com.github.ysl3000.Location.SpawnArea;
import com.github.ysl3000.Location.Teleport;
import com.github.ysl3000.Prefixer.NickName;
import com.github.ysl3000.Prefixer.Top;
import com.github.ysl3000.Utils.Info;
import com.github.ysl3000.Weather.Weather;

public class Commander {

	public void runCommands(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		try {
			Gm.toggleGm((Player) sender, commandLabel, args, cmd);
			Top.toggleop(cmd, sender, args);
			Time.setTime((Player) sender, commandLabel);
			Weather.changeWeather((Player) sender, commandLabel);
			Health.kill(sender, commandLabel, args, cmd);
			Info.infos(sender, commandLabel, args, cmd);
			Teleport.tp((Player) sender, commandLabel, args, cmd);
			SpawnArea.spawn(sender, commandLabel, args, cmd);
			CommandLogger.commandToLog(sender, commandLabel, args, cmd);
			HideP.hide(sender, commandLabel, args, cmd);
			ItemMan.item((Player) sender, commandLabel, args, cmd);
			KickManager.kick(sender, commandLabel, args, cmd);
			EntityManager.removeEntity(sender, commandLabel, args, cmd);
			Inviter.invite(sender, commandLabel, args, cmd);
			Questioner.quest((Player) sender, commandLabel, args, cmd);
			Gm.playerSpeed(sender, commandLabel, args, cmd);
			Gm.godmode(sender, commandLabel, args, cmd);
			ChannelChat.ManageChannel(sender, commandLabel, args, cmd);
			NickName.Nick(sender, commandLabel, args, cmd);
			SSTH.help(sender, commandLabel, args, cmd);
			Gm.freezePlayer(sender, commandLabel, args, cmd);
			Weather.runPlayerWeather(sender, commandLabel, args, cmd);
		} catch (Exception e) {
		}
	}
}

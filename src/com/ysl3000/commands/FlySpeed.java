/**
* FlySpeed.java
* 
* Created on , 14:35:14 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.ysl3000.lib.CustomCommand;

/**
 * @author yannicklamprecht
 *
 */
public class FlySpeed extends CustomCommand{

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param permission
	 * @param cmdexe
	 */
	public FlySpeed() {
		super("fs", "Set flyspeed", "/fs <amount>",
				null, new CommandExecutor() {
					
					@Override
					public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							String[] args) {
						
						if (args.length != 1)
							return false;
						Player p = (Player) sender;
						

						if (Float.parseFloat(args[0]) > 0 && Float.parseFloat(args[0]) <= 1) {
							p.setFlySpeed(Float.parseFloat(args[0]));

							p.sendMessage("Flyspeed set to " + p.getFlySpeed());
						} else {
							p.sendMessage("Speed has to be between 0.1 and 1.0");
						}

						return true;
						
					}
				});
		
	}
}

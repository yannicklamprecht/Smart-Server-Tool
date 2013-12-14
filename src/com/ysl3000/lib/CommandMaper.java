/**
 * CommandMaper.java
 * 
 * Created on , 14:43:35 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 10.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.lib;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Field;

/**
 * @author yannicklamprecht
 * 
 */
public class CommandMaper {

	private CommandMap cmap;
	private JavaPlugin pl;

	public CommandMaper(JavaPlugin pl) {
		this.pl = pl;
		try {
			if (Bukkit.getServer() instanceof CraftServer) {
				final Field f = CraftServer.class
						.getDeclaredField("commandMap");
				f.setAccessible(true);
				cmap = (CommandMap) f.get(Bukkit.getServer());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean register(String s, Command cmd) {
		return cmap.register(s, cmd);
	}

	public boolean register(Command cmd) {
		return register(pl.getName(), cmd);
	}
}

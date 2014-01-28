package com.ysl3000.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.ysl3000.utils.ConfigFactorizer;

import lib.CustomCommand;

public class Configreload extends CustomCommand {

	public Configreload() {
		super("smartreload", "Reloads SmartServerTool config", "/smartreload",
				"sst.reload", new CommandExecutor() {

					@Override
					public boolean onCommand(CommandSender sender, Command cmd,
							String commandLabel, String[] args) {

						ConfigFactorizer.createAndReturn(
								(JavaPlugin) Bukkit.getServer()
										.getPluginManager()
										.getPlugin("SmartServerTool"))
								.loadConfig();
						
						
						
						
						return false;
					}
				});
	}

}

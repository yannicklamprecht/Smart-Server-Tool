/**
 * SmartCommand.java
 * 
 * Created on , 14:34:59 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 10.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.lib;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author yannicklamprecht
 * 
 */
public class CustomCommand extends Command {

	private CommandExecutor exe = null;
	

	/**
	 * @param name
	 * @param description
	 * @param usageMessage
	 * @param aliases
	 */
	 protected CustomCommand(String name, String description,
			String usageMessage, List<String> aliases,String permission) {
		super(name, description, usageMessage, aliases);
		this.setPermissionMessage("You don't have the permission to perform this command");
	}
	 
	 protected CustomCommand(String name, String description,
				String usageMessage){
		 this(name, description,usageMessage,new ArrayList<String>(),"");
	 }
 	 
 	 protected CustomCommand(String name, String description,
				String usageMessage, String permission,CommandExecutor cmdexe){
 		 this(name,description,usageMessage);
 		 this.setPermission(permission);
 		 this.setExecutor(cmdexe);
 	 }
 	 public boolean registerAlias(String alias){
 		return this.getAliases().add(alias);
 	 }
 	 
 	 public boolean registerAliases(List<String> aliases){
 		 return this.getAliases().addAll(this.getAliases().size(), aliases);
 	 }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bukkit.command.Command#execute(org.bukkit.command.CommandSender,
	 * java.lang.String, java.lang.String[])
	 */
	@Override
 	public boolean execute(CommandSender sender, String commandLabel,
			String[] args) {
		if (exe != null) {
			exe.onCommand(sender, this, commandLabel, args);
		}
		return false;
	}

	public void setExecutor(CommandExecutor exe) {
		this.exe = exe;
	}

	public String getHelp(){
		return this.usageMessage+" "+this.description;
	}
	
}
 
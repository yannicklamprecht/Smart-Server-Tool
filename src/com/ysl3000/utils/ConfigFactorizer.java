package com.ysl3000.utils;

import org.bukkit.plugin.java.JavaPlugin;


public class ConfigFactorizer {
private static Config config;
	
	public static Config createAndReturn(JavaPlugin plugin){
		if(config == null){
			config = new Config(plugin);
		}
		return config;
	}
	


}

package com.github.ysl3000;

import org.bukkit.event.Listener;

public class WeatherListener implements Listener{

	public WeatherListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
}

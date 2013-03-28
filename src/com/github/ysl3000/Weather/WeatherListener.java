package com.github.ysl3000.Weather;

import org.bukkit.event.Listener;

import com.github.ysl3000.SmartServerTool;

public class WeatherListener implements Listener{

	public WeatherListener(SmartServerTool plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
}

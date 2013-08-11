package com.ysl3000.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class HashMapController {

	private HashMap<Player,SmartPlayer> players;

	public HashMapController() {
		this.players = new HashMap<Player, SmartPlayer>();
	}

	public HashMap<Player,SmartPlayer> getSmartPLayers(){
		return players;
	}
}

package com.ysl3000.utils;

import java.util.HashMap;
import java.util.UUID;

public class HashMapController {

	private static HashMapController hmc;
	private HashMap<UUID,SmartPlayer> players;
	
	public HashMapController() {
		this.players = new HashMap<UUID, SmartPlayer>();
	}

	public HashMap<UUID,SmartPlayer> getSmartPLayers(){
		return players;
	}
	public static HashMapController getHashMapControler() {
		if (hmc == null) {
			hmc = new HashMapController();
		}
		return hmc;
	}
}

package com.github.ysl3000;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class HashmapHandler {

	private static HashMap<Player, Boolean> isMod = new HashMap<Player, Boolean>();
	private static HashMap<Player, Location> LastL = new HashMap<Player, Location>();
	private static HashMap<Player, Location> currentL = new HashMap<Player, Location>();
	private static HashMap<Player, Boolean> isGod = new HashMap<Player, Boolean>();
	private static HashMap<Player, Boolean> isFly = new HashMap<Player, Boolean>();
	private static HashMap<Player, Boolean> isHidden = new HashMap<Player, Boolean>();

	public HashmapHandler(SmartServerTool plugin) {
		isMod = new HashMap<Player, Boolean>();
		LastL = new HashMap<Player, Location>();
		currentL = new HashMap<Player, Location>();
		isGod = new HashMap<Player, Boolean>();
		isFly = new HashMap<Player, Boolean>();
		isHidden = new HashMap<Player, Boolean>();
	}

	public static boolean getIsMod(Player p) {
		return isMod.get(p);
	}

	public static void setIsMOD(Player p, boolean modstatus) {
		isMod.put(p, modstatus);
	}

	public static void removePlayerMOD(Player p) {
		isMod.remove(p);
	}

	public static Location getLastLocation(Player p) {
		return LastL.get(p);
	}

	public static void setLastLocation(Player p, Location last) {
		LastL.put(p, last);
	}

	public static Location getCurrentLocation(Player p) {
		return currentL.get(p);
	}

	public static void setCurrentLocation(Player p, Location current) {

		currentL.put(p, current);
	}

	public static boolean isGod(Player p) {

		return isGod.get(p);
	}
	public static void setGod(Player p, boolean god){
		
		isGod.put(p, god);
	}
	
	public static boolean isFlyStatus(Player p){
		return isFly.get(p);
	}
	public static void setFlyStatus(Player p , boolean fly){
		isFly.put(p, fly);
	}
	
	public static boolean isHiddenStatus(Player p){
		return isHidden.get(p);
	}
	public static void setHiddenStatus(Player p, boolean hidden){
		isHidden.put(p, hidden);
	}
}

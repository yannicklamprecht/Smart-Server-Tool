package com.github.ysl3000.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.github.ysl3000.SmartServerTool;

public class HashmapHandler {
	private ArrayList<Player> Ap;
	private ArrayList<Player> isMod;
	private ArrayList<Player> isGod;
	private ArrayList<Player> freeze;
	private HashMap<Player, Location> LastL;
	private HashMap<Player, Location> currentL;
	private HashMap<Player, Boolean> isFly;
	private HashMap<String, String> Channel;
	private HashMap<Player, ItemStack[]> inventories;

	public HashmapHandler(SmartServerTool plugin) {
		Ap = new ArrayList<Player>();
		isMod = new ArrayList<Player>();
		isGod = new ArrayList<Player>();
		freeze = new ArrayList<Player>();
		LastL = new HashMap<Player, Location>();
		currentL = new HashMap<Player, Location>();
		isFly = new HashMap<Player, Boolean>();
		Channel = new HashMap<String, String>();
		inventories = new HashMap<Player, ItemStack[]>();
	}

	public boolean isInArray(Player p) {
		return this.Ap.contains(p);
	}

	public void addPlayerToAp(Player p) {
		this.Ap.add(p);
	}

	public void removePlayerFromAp(Player p) {
		this.Ap.remove(p);
	}

	public boolean getIsMod(Player p) {
		return this.isMod.contains(p);
	}

	public void setIsMOD(Player p) {
		this.isMod.add(p);
	}

	public void removeIsMOD(Player p) {
		this.isMod.remove(p);
	}

	public void removePlayerMOD(Player p) {
		this.isMod.remove(p);
	}

	public Location getLastLocation(Player p) {
		return this.LastL.get(p);
	}

	public void setLastLocation(Player p, Location last) {
		this.LastL.put(p, last);
	}

	public boolean hasLastLocation(Player p) {
		return LastL.containsKey(p);
	}

	public void removeLastLocation(Player p) {
		LastL.remove(p);
	}

	public Location getCurrentLocation(Player p) {
		return this.currentL.get(p);
	}

	public void setCurrentLocation(Player p, Location current) {
		this.currentL.put(p, current);
	}

	public boolean isGod(Player p) {
		return this.isGod.contains(p);
	}

	public void addGod(Player p) {
		this.isGod.add(p);
	}

	public void removeGod(Player p) {
		this.isGod.remove(p);
	}

	public boolean isFlyStatus(Player p) {
		return this.isFly.containsKey(p);
	}

	public void setFlyStatus(Player p, boolean fly) {
		this.isFly.put(p, fly);
	}

	public String getChannel(String name) {
		return this.Channel.get(name);
	}

	public void setChannel(String name, String Channel) {
		this.Channel.put(name, Channel);

	}

	public boolean isFrozen(Player p) {
		return freeze.contains(p);
	}

	public void setFrozen(Player p) {
		freeze.add(p);
	}

	public void removeFrozen(Player p) {
		freeze.remove(p);
	}

	public void setInventory(Player p) {
		inventories.put(p, p.getInventory().getContents());
	}

	public ItemStack[] getInventory(Player p) {
		return inventories.get(p);
	}

	public boolean isInventoryIn(Player p) {
		return inventories.containsKey(p);
	}

	public void removeInventory(Player p) {
		inventories.remove(p);
	}

}

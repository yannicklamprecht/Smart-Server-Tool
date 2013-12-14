package com.ysl3000.utils;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SmartPlayer {

	private UUID player;
	private boolean isMod;
	private boolean isGod;
	private boolean isFrozen;
	private Location lastLocation;
	private Location currentLocation;
	private Location modLocation;
	private ItemStack[] inventory;
	private String channel;

	public SmartPlayer(Player player) {
		this.player = player.getUniqueId();
		this.isMod = false;
		this.isGod = false;
		this.isFrozen = false;
		this.lastLocation = player.getLocation();
		this.currentLocation = player.getLocation();
		this.modLocation = player.getLocation();
		this.inventory = player.getInventory().getContents();
		this.channel = "g";
	}

	public UUID getPlayerAsUUID() {
		return player;
	}

	public boolean isMod() {
		return isMod;
	}

	public void setMod(boolean isMod) {
		this.isMod = isMod;
	}

	public boolean isGod() {
		return isGod;
	}

	public void setGod(boolean isGod) {
		this.isGod = isGod;
	}

	public boolean isFrozen() {
		return isFrozen;
	}

	public void setFrozen(boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Location getModLocation() {
		return modLocation;
	}

	public void setModLocation(Location modLocation) {
		this.modLocation = modLocation;
	}

	public ItemStack[] getInventory() {
		return inventory;
	}

	public void setInventory(ItemStack[] inventory) {
		this.inventory = inventory;
	}

	public void setChannel(String s) {
		this.channel = s;
	}

	public String getChannel() {
		return channel;
	}
}

/**
 * TimeThread.java
 * 
 * Created on , 19:11:02 by @author Yannick Lamprecht
 *
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.threads;

import org.bukkit.entity.Player;

import com.ysl3000.utils.SmartController;

/**
 * @author yannicklamprecht
 * 
 */
public class TimeThread extends Thread {

	private long time;
	private Player p;

	public TimeThread(long time, Player p) {
		this.setTime(time);
		this.p = p;
		this.start();
	}

	public Player getPlayer() {
		return p;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void run() {

		SmartController.getSmartControler().getHashmaps().getSmartPLayers()
				.get(this.getPlayer().getUniqueId()).setFrozen(true);
		try {
			Thread.sleep(this.time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		SmartController.getSmartControler().getHashmaps().getSmartPLayers()
				.get(this.getPlayer().getUniqueId()).setFrozen(false);
		p.sendMessage("You're now allowed to move");
	}

}

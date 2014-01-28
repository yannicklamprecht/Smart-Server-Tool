/**
* RepeatingThread.java
* 
* Created on , 16:23:34 by @author Yannick Lamprecht
*
* SmartServerToolRewrote Copyright (C) 19.12.2013  Yannick Lamprecht
* This program comes with ABSOLUTELY NO WARRANTY;
* This is free software, and you are welcome to redistribute it
* under certain conditions;
*/
package com.ysl3000.threads;

/**
 * @author yannicklamprecht
 *
 */
public class RepeatingThread extends Thread{

	private long time;
	private int repeat;
	private TimeAction before;
	private TimeAction after;

	public RepeatingThread(long time,int repeat, TimeAction before, TimeAction after) {
		this.time=time;
		this.before = before;
		this.after = after;
		this.repeat=repeat;
		this.start();
	}
	public void run() {
		
		for(int i = 0; i<repeat; i++){
		before.perform();
		try {
			Thread.sleep(this.time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		after.perform();
		}
	}
	
	
}

/**
 * TimeThread.java
 * <p>
 * Created on , 19:11:02 by @author Yannick Lamprecht
 * <p>
 * SmartServerToolRewrote Copyright (C) 11.12.2013  Yannick Lamprecht
 * This program comes with ABSOLUTELY NO WARRANTY;
 * This is free software, and you are welcome to redistribute it
 * under certain conditions;
 */
package com.ysl3000.threads;


/**
 * @author yannicklamprecht
 *
 */
public class TimeThread extends Thread {

    private long time;
    private TimeAction before;
    private TimeAction after;

    public TimeThread(long time, TimeAction before, TimeAction after) {
        this.setTime(time);
        this.before = before;
        this.after = after;
        this.start();
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void run() {
        before.perform();
        try {
            Thread.sleep(this.time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        after.perform();
    }
}

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

  @Override
  public void run() {
    before.perform();
    try {
      Thread.sleep(this.time);
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
    after.perform();
  }
}

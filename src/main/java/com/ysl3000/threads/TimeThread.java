package com.ysl3000.threads;


import java.util.logging.Logger;

/**
 * @author yannicklamprecht
 */
public class TimeThread extends Thread {

  private static final Logger LOGGER = Logger.getLogger(TimeThread.class.getName());
  private final TimeAction before;
  private final TimeAction after;
  private long time;

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
      LOGGER.throwing(Thread.class.getName(), "sleep", e);
      Thread.currentThread().interrupt();
    }
    after.perform();
  }
}

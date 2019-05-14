package com.ysl3000.stubs;

import be.seeseemelk.mockbukkit.WorldMock;

/**
 * Created by ysl3000
 */
public class SmartWorldStub extends WorldMock {

  private boolean thundering = false;

  @Override
  public boolean isThundering() {
    return thundering;
  }

  @Override
  public void setThundering(boolean thundering) {
    this.thundering = thundering;
  }

  @Override
  public long getSeed() {
    return 1337;
  }
}

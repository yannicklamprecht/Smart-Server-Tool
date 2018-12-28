package com.ysl3000.config.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class ConfigPositionTest {


  private ConfigPosition configPosition;


  @Before
  public void setUp() {
    this.configPosition = new ConfigPosition("SmartServerWorld", 42, 244, 1337);
  }

  @Test
  public void shouldCopyValuesFromOther() {

    ConfigPosition configPositionCheck = new ConfigPosition();

    checkConfigPosition(configPositionCheck, "world", 0, 0, 0);

    configPositionCheck.copyOf(this.configPosition);

    checkConfigPosition(configPositionCheck, "SmartServerWorld", 42, 244, 1337);


  }

  private void checkConfigPosition(ConfigPosition configPosition, String name, double x, double y,
      double z) {
    Assert.assertEquals(name, configPosition.getName());
    Assert.assertEquals(x, configPosition.getX(), 0);
    Assert.assertEquals(y, configPosition.getY(), 0);
    Assert.assertEquals(z, configPosition.getZ(), 0);

  }

}

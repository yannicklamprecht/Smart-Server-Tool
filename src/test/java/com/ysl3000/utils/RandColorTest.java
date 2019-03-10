package com.ysl3000.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class RandColorTest {


  @Test
  public void testNextColor() {
    RandColor randColor = new RandColor();

    Assert.assertEquals(RandColor.VALID_COLORS[0], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[1], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[2], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[3], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[4], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[5], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[6], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[7], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[8], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[9], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[10], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[11], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[12], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[0], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[1], randColor.getNextColor());
    Assert.assertEquals(RandColor.VALID_COLORS[2], randColor.getNextColor());
  }

}

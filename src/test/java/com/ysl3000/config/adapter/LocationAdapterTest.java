package com.ysl3000.config.adapter;

import org.bukkit.Location;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class LocationAdapterTest extends AbstractAdapterTest{


  @Test
  public void shouldConvertFromPositionToLocation() {

    Location location = new LocationAdapter(this.iConfigPosition);

    checkEquality(this.location, location);
  }


  private void checkEquality(Location location, Location location2){
    Assert.assertEquals(location.getWorld(),location2.getWorld());
    Assert.assertEquals(location.getX(),location2.getX(),0);
    Assert.assertEquals(location.getY(),location2.getY(),0);
    Assert.assertEquals(location.getZ(),location2.getZ(),0);

  }
}

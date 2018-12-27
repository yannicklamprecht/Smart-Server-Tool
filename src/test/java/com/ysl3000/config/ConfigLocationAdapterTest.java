package com.ysl3000.config;

import be.seeseemelk.mockbukkit.WorldMock;
import com.ysl3000.config.adapter.ConfigPositionAdapter;
import com.ysl3000.config.adapter.LocationAdapter;
import com.ysl3000.config.data.ConfigPosition;
import com.ysl3000.config.data.IConfigPosition;
import com.ysl3000.stubs.SmartMockBukkit;
import com.ysl3000.stubs.SmartServerMock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ysl3000
 */
public class ConfigLocationAdapterTest {


  private IConfigPosition iConfigPosition;

  private Location location;

  @Before
  public void setUp() {

    World world = new WorldMock();
    ((WorldMock) world).setName("world");

    SmartServerMock smartServerMock = new SmartServerMock(world);
    if (Bukkit.getServer() == null) {
      Bukkit.setServer(smartServerMock);
    }

    this.iConfigPosition = new ConfigPosition("world", 1337, 100, 42);


    this.location = new Location(world, 1337, 100, 42);
  }

  @After
  public void tearDown(){
    SmartMockBukkit.unset();
  }

  @Test
  public void shouldConvertFromPositionToLocation() {

    Location location = new LocationAdapter(this.iConfigPosition);

    checkEquality(this.location, location);
  }

  @Test
  public void shouldConvertFromLocationToPosition() {

    IConfigPosition position = new ConfigPositionAdapter(this.location);

    checkEquality(this.iConfigPosition,position);


  }

  private void checkEquality(Location location, Location location2){
    Assert.assertEquals(location.getWorld(),location2.getWorld());
    Assert.assertEquals(location.getX(),location2.getX(),0);
    Assert.assertEquals(location.getY(),location2.getY(),0);
    Assert.assertEquals(location.getZ(),location2.getZ(),0);

  }


  private void checkEquality(IConfigPosition location, IConfigPosition location2){
    Assert.assertEquals(location.getName(),location2.getName());
    Assert.assertEquals(location.getX(),location2.getX(),0);
    Assert.assertEquals(location.getY(),location2.getY(),0);
    Assert.assertEquals(location.getZ(),location2.getZ(),0);

  }

}

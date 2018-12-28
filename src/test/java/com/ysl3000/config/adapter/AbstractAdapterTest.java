package com.ysl3000.config.adapter;

import be.seeseemelk.mockbukkit.WorldMock;
import com.ysl3000.config.data.ConfigPosition;
import com.ysl3000.config.data.IConfigPosition;
import com.ysl3000.stubs.SmartMockBukkit;
import com.ysl3000.stubs.SmartServerMock;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.junit.After;
import org.junit.Before;

/**
 * Created by ysl3000
 */
public abstract class AbstractAdapterTest {


  protected IConfigPosition iConfigPosition;

  protected Location location;

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

}

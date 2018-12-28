package com.ysl3000.stubs;

import be.seeseemelk.mockbukkit.ServerMock;
import org.bukkit.World;

/**
 * Created by ysl3000
 */
public class SmartServerMock extends ServerMock {

  private World world;

  public SmartServerMock(World world) {
    this.world = world;
  }

  @Override
  public String getServerName() {
    return "SmartServer";
  }

  @Override
  public World getWorld(String name) {
    return world;
  }
}

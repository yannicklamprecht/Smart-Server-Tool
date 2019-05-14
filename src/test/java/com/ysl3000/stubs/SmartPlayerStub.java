package com.ysl3000.stubs;

import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.entity.PlayerMock;
import java.net.InetSocketAddress;
import java.util.UUID;
import org.bukkit.World;

/**
 * Created by ysl3000
 */
public class SmartPlayerStub extends PlayerMock {

  private boolean playedBefore = false;
  private InetSocketAddress inetSocketAddress;
  private World world;

  public SmartPlayerStub(ServerMock server, String name,
      InetSocketAddress inetSocketAddress, World world) {
    super(server, name);
    this.inetSocketAddress = inetSocketAddress;
    this.world = world;
  }

  public SmartPlayerStub(ServerMock server, String name, UUID uuid,
      InetSocketAddress inetSocketAddress, World world) {
    super(server, name, uuid);
    this.inetSocketAddress = inetSocketAddress;
    this.world = world;
  }

  @Override
  public boolean hasPlayedBefore() {
    return playedBefore;
  }

  public void setPlayedBefore(boolean playedBefore) {
    this.playedBefore = playedBefore;
  }

  @Override
  public InetSocketAddress getAddress() {
    return inetSocketAddress;
  }

  @Override
  public float getFlySpeed() {
    return 1.0f;
  }

  @Override
  public float getWalkSpeed() {
    return 1.0f;
  }

  @Override
  public World getWorld() {
    return world;
  }
}

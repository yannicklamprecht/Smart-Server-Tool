package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.Server;

/**
 * Created by ysl3000
 */
public class BukkitVersionMapper implements ValueMapper {

  private final Server server;

  public BukkitVersionMapper(Server server) {
    this.server = server;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.replace("{bukkit_version}", server.getBukkitVersion());
  }
}

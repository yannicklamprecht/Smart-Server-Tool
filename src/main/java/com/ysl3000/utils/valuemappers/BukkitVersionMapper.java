package com.ysl3000.utils.valuemappers;

import com.ysl3000.utils.MessageWrapper;
import org.bukkit.Server;

/**
 * Created by ysl3000
 */
public class BukkitVersionMapper implements ValueMapper {

  private Server server;

  public BukkitVersionMapper(Server server) {
    this.server = server;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.replace("{bukkit_version}",server.getBukkitVersion());
  }
}

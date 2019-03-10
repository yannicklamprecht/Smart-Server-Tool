package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.Server;

/**
 * Created by ysl3000
 */
public class ServerNameMapper implements ValueMapper {


  private Server server;

  public ServerNameMapper(Server server) {
    this.server = server;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.replace("{server_name}", server.getName());
  }
}

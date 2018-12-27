package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.Server;

/**
 * Created by ysl3000
 */
public class PlayersOnlineMapper implements ValueMapper {

  private Server server;
  private Utility utility;

  public PlayersOnlineMapper(Server server, Utility utility) {
    this.server = server;
    this.utility = utility;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message
        .replace("{online_players}", String.valueOf(server.getOnlinePlayers().size()))
        .replace("{max_players}", String.valueOf(server.getMaxPlayers()))
        .replace("{list_players}", utility.listPlayers());
  }
}

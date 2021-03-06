package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.OfflinePlayer;

/**
 * Created by ysl3000
 */
public class PlayerNameMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(OfflinePlayer.class)
        .ifPresent(player -> message.replace("{player_name}", player.getName()));
  }
}

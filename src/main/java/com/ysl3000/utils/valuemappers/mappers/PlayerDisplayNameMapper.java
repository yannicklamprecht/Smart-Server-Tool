package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class PlayerDisplayNameMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(Player.class)
        .ifPresent(player -> {
          message.replace("{player_display_name}", player.getDisplayName());
        });

  }
}

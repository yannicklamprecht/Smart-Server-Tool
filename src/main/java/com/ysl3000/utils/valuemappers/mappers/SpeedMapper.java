package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class SpeedMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(Player.class).ifPresent(player -> {
      message.replace("{fly_speed}", String.valueOf(player.getFlySpeed()));
      message.replace("{walk_speed}", String.valueOf(player.getWalkSpeed()));
    });
  }
}

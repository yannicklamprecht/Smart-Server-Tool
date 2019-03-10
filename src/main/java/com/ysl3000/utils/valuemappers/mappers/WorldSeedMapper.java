package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class WorldSeedMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(Player.class).ifPresent(
        player -> message.replace("{world_seed}", String.valueOf(player.getWorld().getSeed())));
  }
}

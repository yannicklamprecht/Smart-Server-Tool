package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.World;

/**
 * Created by ysl3000
 */
public class WorldMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(World.class).ifPresent(world -> message.replace("{world_name}", world.getName()));
  }
}

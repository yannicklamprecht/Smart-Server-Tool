package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class GamemodeMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(Player.class).ifPresent(p -> message.replace("{player_gamemode}", p.getGameMode().name()));
    message.get(GameMode.class).ifPresent(g -> message.replace("{gamemode}",g.name()));
  }
}

package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.config.settings.messages.PlayerMessage;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
public class FirstJoinMapper implements ValueMapper {

  private final String firstJoinMessage;

  public FirstJoinMapper(PlayerMessage playerMessage) {
    this.firstJoinMessage = playerMessage.getFirstJoin();
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(Player.class).ifPresent(player -> {
      if (player.hasPlayedBefore()) {
        message.replace("{first_join_message}", "");
      } else {
        message.replace("{first_join_message}", firstJoinMessage);
      }
    });
  }
}

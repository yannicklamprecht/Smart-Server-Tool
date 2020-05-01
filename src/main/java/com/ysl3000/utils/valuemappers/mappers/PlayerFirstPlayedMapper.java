package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.OfflinePlayer;

/**
 * Created by ysl3000
 */
public class PlayerFirstPlayedMapper implements ValueMapper {

  private final Utility utility;
  private final Messages messages;

  public PlayerFirstPlayedMapper(Utility utility, Messages messages) {
    this.utility = utility;
    this.messages = messages;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {

    message.get(OfflinePlayer.class).ifPresent(offlinePlayer -> {
      if (offlinePlayer.hasPlayedBefore()) {
        message.replace("{first_played}",
            utility.getTime(offlinePlayer.getFirstPlayed(), messages.getTimeformat()));
        message.replace("{last_played}",
            utility.getTime(offlinePlayer.getLastPlayed(), messages.getTimeformat()));
      } else {
        message.replace("{first_played}", "never");
        message.replace("{last_played}", "never");
      }
    });
  }
}

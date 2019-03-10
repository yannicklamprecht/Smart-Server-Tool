package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

/**
 * Created by ysl3000
 */
public class LoginResultMapper implements ValueMapper {


  @Override
  public void injectPlaceholder(MessageWrapper message) {

    message.get(PlayerLoginEvent.Result.class).ifPresent(result -> {
      String loginResult = "";
      if (result == Result.KICK_WHITELIST) {
        loginResult = "whitelist";
      } else if (result == Result.KICK_BANNED) {
        loginResult = "banned";
      }
      message.replace("{login_result}", loginResult);
    });
  }
}

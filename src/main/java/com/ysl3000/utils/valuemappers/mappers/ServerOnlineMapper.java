package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;

/**
 * Created by ysl3000
 */
public class ServerOnlineMapper implements ValueMapper {

  private static final Pattern ONLINE_PATTERN = MapperPattern
      .createPatternFromPrefix("server_online_mode");

  @Override
  public void injectPlaceholder(MessageWrapper message) {

    Matcher matcher = ONLINE_PATTERN.matcher(message.getMessage());
    if (matcher.find()) {
      message.setMessage(
          matcher.replaceAll(Bukkit.getOnlineMode() ? matcher.group(1) : matcher.group(2)));
    }
  }
}
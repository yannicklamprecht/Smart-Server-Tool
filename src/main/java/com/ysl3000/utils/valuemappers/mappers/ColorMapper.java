package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import org.bukkit.ChatColor;

/**
 * Created by ysl3000
 */
public class ColorMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.setMessage(ChatColor.translateAlternateColorCodes('&', message.getMessage()));
  }
}

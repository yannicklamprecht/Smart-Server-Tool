package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.SmartPlayer;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ysl3000
 */
public class GodMapper implements ValueMapper {

  private static final Pattern GOD_PATTERN = MapperPattern.createPatternFromPrefix("god");

  @Override
  public void injectPlaceholder(MessageWrapper message) {

    message.get(SmartPlayer.class).ifPresent(smartPlayer -> {
      Matcher matcher = GOD_PATTERN.matcher(message.getMessage());
      if (matcher.find()) {
        message.setMessage(
            matcher.replaceAll(smartPlayer.isGod() ? matcher.group(1) : matcher.group(2)));
      }
    });

  }
}

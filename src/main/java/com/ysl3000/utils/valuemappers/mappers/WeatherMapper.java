package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.World;

/**
 * Created by ysl3000
 */
public class WeatherMapper implements ValueMapper {

  private static final Pattern WEATHER_PATTERN = MapperPattern.createPatternFromPrefix("weather");

  @Override
  public void injectPlaceholder(MessageWrapper message) {

    message.get(World.class).ifPresent(world -> {
      Matcher matcher = WEATHER_PATTERN.matcher(message.getMessage());
      if (matcher.find()) {
        message.setMessage(
            matcher.replaceAll(world.isThundering() ? matcher.group(1) : matcher.group(2)));
      }
    });


  }
}

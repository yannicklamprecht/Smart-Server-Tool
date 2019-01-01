package com.ysl3000.utils.valuemappers;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.mappers.BukkitVersionMapper;
import com.ysl3000.utils.valuemappers.mappers.ColorMapper;
import com.ysl3000.utils.valuemappers.mappers.CpuCoreMapper;
import com.ysl3000.utils.valuemappers.mappers.FirstJoinMapper;
import com.ysl3000.utils.valuemappers.mappers.GamemodeMapper;
import com.ysl3000.utils.valuemappers.mappers.LoginResultMapper;
import com.ysl3000.utils.valuemappers.mappers.MinecraftVersionMapper;
import com.ysl3000.utils.valuemappers.mappers.PlayerDisplayNameMapper;
import com.ysl3000.utils.valuemappers.mappers.PlayerNameMapper;
import com.ysl3000.utils.valuemappers.mappers.PlayersOnlineMapper;
import com.ysl3000.utils.valuemappers.mappers.ServerNameMapper;
import com.ysl3000.utils.valuemappers.mappers.TimeMapper;
import com.ysl3000.utils.valuemappers.mappers.WeatherMapper;
import com.ysl3000.utils.valuemappers.mappers.WorldMapper;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Server;

/**
 * Created by ysl3000
 */
public class MessageBuilder {

  private List<ValueMapper> mappers = new ArrayList<>();

  public MessageBuilder(Server server, Messages messages, Utility utility) {
    mappers.add(new BukkitVersionMapper(server));
    mappers.add(new CpuCoreMapper());
    mappers.add(new MinecraftVersionMapper(server));
    mappers.add(new PlayerDisplayNameMapper());
    mappers.add(new PlayerNameMapper());
    mappers.add(new PlayersOnlineMapper(server, utility));
    mappers.add(new ServerNameMapper(server));
    mappers.add(new TimeMapper(messages));
    mappers.add(new LoginResultMapper());
    mappers.add(new FirstJoinMapper(messages.getPlayer()));
    mappers.add(new GamemodeMapper());
    mappers.add(new WorldMapper());
    mappers.add(new WeatherMapper());
    mappers.add(new ColorMapper());
  }

  public MessageWrapper replaceMessageValues(MessageWrapper messageWrapper) {
    mappers.forEach(valueMapper -> valueMapper.injectPlaceholder(messageWrapper));
    return messageWrapper;
  }

  public String injectParameter(String message, Object... objects) {
    return replaceMessageValues(MessageWrapper.of(message, objects)).getMessage();
  }

}

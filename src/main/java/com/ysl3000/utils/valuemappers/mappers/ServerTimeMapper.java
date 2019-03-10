package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.Utility;
import com.ysl3000.utils.valuemappers.ValueMapper;

/**
 * Created by ysl3000
 */
public class ServerTimeMapper implements ValueMapper {

  private Utility utility;
  private Messages messages;

  public ServerTimeMapper(Utility utility, Messages messages) {
    this.utility = utility;
    this.messages = messages;
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.replace("{server_time}",
        utility.getTime(System.currentTimeMillis(), messages.getTimeformat()));
  }
}

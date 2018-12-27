package com.ysl3000.utils.valuemappers;

import com.ysl3000.config.settings.Messages;
import com.ysl3000.utils.MessageWrapper;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ysl3000
 */
public class TimeMapper implements ValueMapper {

  private SimpleDateFormat dateFormat;

  public TimeMapper(Messages messages) {
    this.dateFormat = new SimpleDateFormat(messages.getTimeformat());
  }

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.replace("{time}", dateFormat.format(new Date(System.currentTimeMillis())));
  }
}

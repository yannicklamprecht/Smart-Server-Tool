package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;

/**
 * Created by ysl3000
 */
public class ParameterMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    message.get(String.class).ifPresent(s -> message.replace("{parameter}", s));
  }
}

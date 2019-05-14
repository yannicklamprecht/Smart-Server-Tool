package com.ysl3000.utils.valuemappers.mappers;

import com.ysl3000.utils.MessageWrapper;
import com.ysl3000.utils.valuemappers.ValueMapper;

/**
 * Created by ysl3000
 */
public class MemoryMapper implements ValueMapper {

  @Override
  public void injectPlaceholder(MessageWrapper message) {
    double total = (((Runtime.getRuntime().totalMemory()) / 1024.0) / 1024);
    double max = (((Runtime.getRuntime().maxMemory()) / 1024.0) / 1024);

    message.replace("{total_memory}", String.valueOf(total));
    message.replace("{max_memory}", String.valueOf(max));
  }
}

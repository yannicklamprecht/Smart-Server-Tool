package com.ysl3000.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by ysl3000
 */
public class MessageWrapper {

  private String message;
  private Map<Class<?>, Object> data = new HashMap<>();

  public MessageWrapper(String message, Object... objects) {
    this.message = message;
    for (Object o : objects) {
      data.put(o.getClass(), o);
    }
  }

  public <T> Optional<T> get(Class<T> tClass) {
    return Optional.ofNullable((T) data.get(tClass));
  }


  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public MessageWrapper replace(String placeholder, String value){
    this.message = this.message.replace(placeholder,value);
    return this;
  }

  public static MessageWrapper of(String message, Object... objects){
    return new MessageWrapper(message,objects);
  }
}

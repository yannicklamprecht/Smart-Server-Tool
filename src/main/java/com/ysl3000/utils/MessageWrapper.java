package com.ysl3000.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Created by ysl3000
 */
public class MessageWrapper {

  private final Map<Class<?>, Object> data = new HashMap<>();
  private String message;

  public MessageWrapper(String message, Object... objects) {
    this.message = message;
    for (Object o : objects) {
      data.put(o.getClass().getSuperclass(), o);
    }
  }

  public static MessageWrapper of(String message, Object... objects) {
    return new MessageWrapper(message, objects);
  }

  public <T> void addData(Class<T> tClass, T object) {
    data.put(tClass, object);
  }

  public <T> Optional<T> get(Class<T> tClass) {

    Object dataObject = data.get(tClass);
    if (dataObject == null) {
      Optional<T> optionalData = data.values().stream().filter(tClass::isInstance).map(tClass::cast)
          .findFirst();
      optionalData.ifPresent(od -> data.put(tClass, od));
      return optionalData;
    }
    return Optional.ofNullable((T) dataObject);
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public MessageWrapper replace(String placeholder, String value) {
    if (message.contains(placeholder)) {
      this.message = this.message.replace(placeholder, value);
    }
    return this;
  }

  public MessageWrapper replace(Pattern pattern, String value) {
    if (pattern.matcher(message).matches()) {
      this.message = pattern.matcher(message).replaceAll(value);
    }
    return this;
  }
}

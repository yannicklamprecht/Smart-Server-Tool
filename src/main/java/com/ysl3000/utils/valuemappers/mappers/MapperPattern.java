package com.ysl3000.utils.valuemappers.mappers;

import java.util.regex.Pattern;

/**
 * Created by ysl3000
 */
final class MapperPattern {
  private MapperPattern(){}

  static final String OPTION_PATTERN = "\\{([§&\\w]*):([§&\\w]*)}";

  static Pattern createPatternFromPrefix(String prefix){
    return Pattern
        .compile("\\{"+prefix+MapperPattern.OPTION_PATTERN+"}",Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
  }

}

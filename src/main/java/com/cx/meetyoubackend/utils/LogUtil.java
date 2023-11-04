package com.cx.meetyoubackend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

  private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

  public static void debug(String message) {
    logger.debug(message);
  }

  public static void info(String message) {
    logger.info(message);
  }

  public static void warn(String message) {
    logger.warn(message);
  }

  public static void error(String message) {
    logger.error(message);
  }

  public static void error(String message, Throwable throwable) {
    logger.error(message, throwable);
  }
}

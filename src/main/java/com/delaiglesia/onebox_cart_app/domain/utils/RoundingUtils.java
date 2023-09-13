package com.delaiglesia.onebox_cart_app.domain.utils;

public class RoundingUtils {

  private RoundingUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static double roundThreeDecimals(final double value) {
    return Math.round(value * 1000.0) / 1000.0;
  }

  public static double roundTwoDecimals(final double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}

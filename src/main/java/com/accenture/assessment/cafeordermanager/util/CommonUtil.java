package com.accenture.assessment.cafeordermanager.util;

import com.accenture.assessment.cafeordermanager.model.entity.Price;
import lombok.experimental.UtilityClass;

/** Utility class for common methods or reusable logic */
@UtilityClass
public class CommonUtil {

  /**
   * Gets price of the drink based on size.
   *
   * @param price - {@code Price} price of the product
   * @param size - {@code String} size of the drink
   * @return {@code String} price amount of the drink
   */
  public String getAmountBySize(final Price price, final String size) {
    switch (size.toUpperCase()) {
      case "SMALL":
        return price.getSmall();
      case "MEDIUM":
        return price.getMedium();
      case "LARGE":
        return price.getLarge();
      case "HUGE":
        return price.getHuge();
      case "MEGA":
        return price.getMega();
      case "ULTRA":
        return price.getUltra();
      default:
        return "";
    }
  }
}

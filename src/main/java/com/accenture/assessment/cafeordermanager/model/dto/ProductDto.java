package com.accenture.assessment.cafeordermanager.model.dto;

import com.accenture.assessment.cafeordermanager.model.entity.Price;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

  private String drink_name;
  private Price prices;
}

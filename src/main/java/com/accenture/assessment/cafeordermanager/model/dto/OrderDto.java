package com.accenture.assessment.cafeordermanager.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {

  private String user;
  private String drink;
  private String size;
}

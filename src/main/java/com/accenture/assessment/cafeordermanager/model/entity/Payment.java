package com.accenture.assessment.cafeordermanager.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
  @Id @GeneratedValue private Long id;

  private String user;

  private BigDecimal amount;
}

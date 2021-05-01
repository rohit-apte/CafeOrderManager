package com.accenture.assessment.cafeordermanager.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {

  @Id private String drinkName;

  @OneToOne(
      mappedBy = "product",
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  private Price price;
}

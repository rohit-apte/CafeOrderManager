package com.accenture.assessment.cafeordermanager.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "prices")
public class Price {

  @Id @GeneratedValue private Long id;

  @OneToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
      optional = false,
      fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id", unique = true)
  //    @MapsId
  private Product product;

  private String small;
  private String medium;
  private String large;
  private String huge;
  private String mega;
  private String ultra;
}

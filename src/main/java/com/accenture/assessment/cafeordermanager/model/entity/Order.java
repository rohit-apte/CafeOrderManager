package com.accenture.assessment.cafeordermanager.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {

  @Id @GeneratedValue private Long id;

  private String user;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product drink;

  private String size;
}

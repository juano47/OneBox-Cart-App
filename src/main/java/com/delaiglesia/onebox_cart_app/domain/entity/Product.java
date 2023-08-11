package com.delaiglesia.onebox_cart_app.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
  private Long id;
  private String name;
  private String description;
  private Double price;
}

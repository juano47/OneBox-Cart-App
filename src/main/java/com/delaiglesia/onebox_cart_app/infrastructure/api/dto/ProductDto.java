package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
  private Long id;
  private String name;
  private String description;
  private double price;
}

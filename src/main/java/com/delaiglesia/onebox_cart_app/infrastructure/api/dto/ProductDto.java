package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto implements Serializable {
  private Long id;
  private String name;
  private String description;
  private double price;
}

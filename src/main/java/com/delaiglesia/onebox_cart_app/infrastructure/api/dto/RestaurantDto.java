package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDto implements Serializable {
  private Long id;
  private String name;
  private List<ProductDto> products;
}

package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
  private Long id;

  @NotNull(message = "product is required")
  private ProductDto product;

  @NotNull(message = "quantity is required")
  @Min(value = 1, message = "quantity must be greater than or equal to 1")
  private int quantity;

  private double price;
}

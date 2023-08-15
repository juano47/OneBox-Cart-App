package com.delaiglesia.onebox_cart_app.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItem implements Serializable {
  private Long id;
  private Product product;
  private int quantity;
  private double price;
}

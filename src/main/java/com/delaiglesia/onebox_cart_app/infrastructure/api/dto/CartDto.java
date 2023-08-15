package com.delaiglesia.onebox_cart_app.infrastructure.api.dto;

import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.entity.CustomerAddress;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto implements Serializable {
  private Long id;
  private List<CartItemDto> items;
  private double total;
  private CustomerDto customer;
  private CartStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime deletedAt;
  private CustomerAddress shippingAddress;
}

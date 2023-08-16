package com.delaiglesia.onebox_cart_app.domain.services.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
  @Override
  public Double calculateTotalPrice(final List<CartItem> items) {
    if (items == null || items.isEmpty()) {
      return 0.0;
    }
    return items.stream().mapToDouble(CartItem::getPrice).sum();
  }
}

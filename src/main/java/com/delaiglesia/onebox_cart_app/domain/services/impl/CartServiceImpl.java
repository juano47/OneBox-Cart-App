package com.delaiglesia.onebox_cart_app.domain.services.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import com.delaiglesia.onebox_cart_app.domain.services.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
  @Override
  public Double calculateTotalPrice(final List<CartItem> items) {
    if (items == null || items.isEmpty()) {
      return 0.0;
    }
    return items.stream().mapToDouble(CartItem::getPrice).sum();
  }
}

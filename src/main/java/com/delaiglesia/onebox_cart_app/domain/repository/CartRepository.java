package com.delaiglesia.onebox_cart_app.domain.repository;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;

import java.util.List;

public interface CartRepository {
	Cart saveCart(Cart cart);

  Cart getCart(Long id);

  void removeCart(Long id);

  List<Cart> getAllCartsByStatus(CartStatus status);
}

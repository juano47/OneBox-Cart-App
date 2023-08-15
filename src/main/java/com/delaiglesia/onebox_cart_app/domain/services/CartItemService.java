package com.delaiglesia.onebox_cart_app.domain.services;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;

import java.util.List;

public interface CartItemService {
	 List<CartItem> setCartItems(final List<CartItem> items, Cart cart);
}

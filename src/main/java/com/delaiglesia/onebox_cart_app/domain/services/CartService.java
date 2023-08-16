package com.delaiglesia.onebox_cart_app.domain.services;

import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;

import java.util.List;

public interface CartService {
	Double calculateTotalPrice(final List<CartItem> items);
}

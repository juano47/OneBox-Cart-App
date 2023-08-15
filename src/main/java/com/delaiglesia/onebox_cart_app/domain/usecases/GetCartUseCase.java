package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetCartUseCase {
	private final CartRepository cartRepository;

	public Cart execute(final Long id) {
		return cartRepository.getCart(id);
	}
}

package com.delaiglesia.onebox_cart_app.domain.usecases.cart;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class GetCartUseCase {
	private final CartRepository cartRepository;

	public Cart execute(final Long id) {
		return cartRepository.getCart(id);
	}
}

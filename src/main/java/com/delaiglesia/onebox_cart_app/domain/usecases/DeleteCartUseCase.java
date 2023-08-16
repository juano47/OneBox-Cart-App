package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteCartUseCase {

	private final CartRepository cartRepository;
	public void execute(Long id) {
		Cart cartDb = cartRepository.getCart(id);
		cartRepository.removeCart(cartDb.getId());
	}
}

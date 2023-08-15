package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetCartsUseCase {
	private final CartRepository cartRepository;

	public List<Cart> execute(String status) {

		status = status.toUpperCase();

		try {
			CartStatus.valueOf(status);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Status is not valid");
		}

		return cartRepository.getAllCartsByStatus(CartStatus.valueOf(status));
	}
}

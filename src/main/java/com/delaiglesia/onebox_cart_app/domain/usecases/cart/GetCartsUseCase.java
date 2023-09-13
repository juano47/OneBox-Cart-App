package com.delaiglesia.onebox_cart_app.domain.usecases.cart;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.DomainPage;
import com.delaiglesia.onebox_cart_app.domain.repository.DomainPageable;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
public class GetCartsUseCase {
  private final CartRepository cartRepository;

  public DomainPage<Cart> execute(DomainPageable pageable, String status) {

    status = status.toUpperCase();

    try {
      CartStatus.valueOf(status);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Status is not valid");
    }

    return cartRepository.getAllCartsByStatus(pageable, CartStatus.valueOf(status));
  }
}

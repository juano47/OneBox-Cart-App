package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateCartUseCase {
  private final CartRepository cartRepository;

  private final CustomerRepository customerRepository;

  private final CartItemService cartItemService;

  private final CartService cartService;

  public Cart execute(final Cart cart) {
    if (cart.getCustomer() == null) {
      throw new IllegalArgumentException("Customer must be set when creating a cart");
    }

    // check if the customer exists
    Customer customerDb = customerRepository.getCustomer(cart.getCustomer().getId());
    if (customerDb == null) {
      throw new IllegalArgumentException("Customer does not exist");
    }

    cart.setCreatedAt(LocalDateTime.now());
    cart.setUpdatedAt(LocalDateTime.now());
    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      cart.setStatus(CartStatus.EMPTY);
    } else {
      cart.setStatus(CartStatus.ACTIVE);

      cart.setItems(cartItemService.setCartItems(cart.getItems(), null));
      cart.setTotal(cartService.calculateTotalPrice(cart.getItems()));
    }
    return cartRepository.saveCart(cart);
  }
}

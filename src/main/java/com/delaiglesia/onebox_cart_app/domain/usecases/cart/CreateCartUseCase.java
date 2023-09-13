package com.delaiglesia.onebox_cart_app.domain.usecases.cart;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import com.delaiglesia.onebox_cart_app.domain.services.CartService;
import com.delaiglesia.onebox_cart_app.domain.usecases.UseCase;
import com.delaiglesia.onebox_cart_app.domain.utils.RoundingUtils;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@UseCase
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
    if (cart.getCustomer().getId() != null) {
      customerRepository.getCustomer(cart.getCustomer().getId());
    }

    cart.setCreatedAt(LocalDateTime.now());
    cart.setUpdatedAt(LocalDateTime.now());
    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      cart.setStatus(CartStatus.EMPTY);
    } else {
      cart.setStatus(CartStatus.ACTIVE);

      cart.setItems(cartItemService.setCartItems(cart.getItems(), null));
      double total =
          RoundingUtils.roundTwoDecimals(cartService.calculateTotalPrice(cart.getItems()));
      // check if the total became from the client is equal to the total calculated
      if (cart.getTotal() != total) {
        throw new IllegalArgumentException("Total price is not correct");
      }
      cart.setTotal(total);
    }
    return cartRepository.saveCart(cart);
  }
}

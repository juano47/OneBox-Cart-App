package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCartUseCase {
  private final CartRepository cartRepository;
  private final CartItemService cartItemService;

  public Cart execute(final Long id, final Cart cart) {
    Cart cartDb = cartRepository.getCart(id);

    // check finals status
    if (cartDb.getStatus() == CartStatus.EXPIRED
        || cartDb.getStatus() == CartStatus.USER_REMOVED
        || cartDb.getStatus() == CartStatus.COMPLETED
        || cartDb.getStatus() == CartStatus.CANCELLED
        || cartDb.getStatus() == CartStatus.USER_CANCELLED) {
      throw new IllegalArgumentException("The cart is in a final status and cannot be updated");
    }

    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      cartDb.setStatus(CartStatus.EMPTY);
    } else {
      cartDb.setStatus(CartStatus.ACTIVE);
    }

    cartDb.setItems(cartItemService.setCartItems(cart.getItems(), cartDb));
    updateTotalPrice(cartDb, cart.getItems());

    if (cart.getShippingAddress() != null) {
      cartDb.setShippingAddress(cart.getShippingAddress());
    }
    cartDb.setUpdatedAt(LocalDateTime.now());

    return cartRepository.saveCart(cartDb);
  }

  private void updateTotalPrice(final Cart cartDb, final List<CartItem> items) {
    if (items == null || items.isEmpty()) {
      cartDb.setTotal(0.0);
      return;
    }

    items.stream().map(CartItem::getPrice).reduce(Double::sum).ifPresent(cartDb::setTotal);
  }
}

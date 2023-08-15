package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateCartUseCase {
  private final CartRepository cartRepository;

  private final ProductRepository productRepository;

  public Cart execute(final Long id, final Cart cart) {
    Cart cartDb = cartRepository.getCart(id);

    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      cartDb.setStatus(CartStatus.EMPTY);
    } else {
      cartDb.setStatus(CartStatus.ACTIVE);
    }

    updateCartItems(cartDb, cart.getItems());
    updateTotalPrice(cartDb, cart.getItems());

    if (cart.getShippingAddress() != null) {
      cartDb.setShippingAddress(cart.getShippingAddress());
    }
    cartDb.setUpdatedAt(LocalDateTime.now());

		return cartRepository.saveCart(cartDb);
  }

  private void updateCartItems(final Cart cartDb, final List<CartItem> items) {
    for (CartItem item : items) {
      // add new items
      if (item.getId() == null) {
        // check if the product exists
        if (productRepository.getProduct(item.getProduct().getId()) == null) {
          throw new IllegalArgumentException(
              "Product with id " + item.getProduct().getId() + " not found");
        }
        cartDb.getItems().add(item);
      } else { // update existing items
        CartItem itemDb =
            cartDb.getItems().stream()
                .filter(i -> i.getId().equals(item.getId()))
                .findFirst()
                .orElseThrow(
                    () ->
                        new IllegalArgumentException(
                            "Item with id " + item.getId() + " not found"));
        int quantity = item.getQuantity();
        itemDb.setQuantity(quantity);
        itemDb.setPrice(item.getProduct().getPrice() * quantity);
      }
    }
  }

  private void updateTotalPrice(final Cart cartDb, final List<CartItem> items) {
    if (items == null || items.isEmpty()) {
      cartDb.setTotal(0.0);
      return;
    }

    items.stream().map(CartItem::getPrice).reduce(Double::sum).ifPresent(cartDb::setTotal);
  }
}

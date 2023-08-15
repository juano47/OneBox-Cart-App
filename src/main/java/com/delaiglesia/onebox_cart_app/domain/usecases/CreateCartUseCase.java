package com.delaiglesia.onebox_cart_app.domain.usecases;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.entity.Customer;
import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.CustomerRepository;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class CreateCartUseCase {
  private final CartRepository cartRepository;

  private final CustomerRepository customerRepository;

  private final ProductRepository productRepository;

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
    if (cart.getItems() == null || cart.getItems().isEmpty()) {
      cart.setStatus(CartStatus.EMPTY);
    } else {
      cart.setStatus(CartStatus.ACTIVE);

      cart.setItems(setCartItems(cart.getItems()));
    }
    return cartRepository.saveCart(cart);
  }

  private List<CartItem> setCartItems(List<CartItem> items) {
    for (CartItem item : items) {
      if (item.getProduct().getId() == null) {
        throw new IllegalArgumentException("Product id must be set");
      }

      Product product = productRepository.getProduct(item.getProduct().getId());
      // check if the product exists
      if (product == null) {
        throw new IllegalArgumentException("Product does not exist");
      }
      item.setProduct(product);

      item.setPrice(product.getPrice() * item.getQuantity());
    }
    return items;
  }
}

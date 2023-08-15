package com.delaiglesia.onebox_cart_app.domain.services.impl;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import com.delaiglesia.onebox_cart_app.domain.services.CartItemService;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

  private final ProductRepository productRepository;

  @Override
  public List<CartItem> setCartItems(List<CartItem> items, Cart cartDb) {
    for (CartItem item : items) {
      int quantity = item.getQuantity();
      Product product = productRepository.getProduct(item.getProduct().getId());
      // add new items
      if (item.getId() == null) {
        if (item.getProduct().getId() == null) {
          throw new IllegalArgumentException("Product id must be set");
        }

        // check if the product exists
        if (product == null) {
          throw new IllegalArgumentException(
              "Product with id " + item.getProduct().getId() + " not found");
        }
        item.setProduct(product);

      } else { // update existing items
        // check if the item exists
        Optional<CartItem> cartItemDb =
            cartDb.getItems().stream().filter(i -> i.getId().equals(item.getId())).findFirst();
        if (cartItemDb.isEmpty()) {
          throw new IllegalArgumentException(
              "Item with id " + item.getId() + " does not exist. Cannot update");
        }
      }

      item.setPrice(product.getPrice() * quantity);
    }
    return items;
  }
}

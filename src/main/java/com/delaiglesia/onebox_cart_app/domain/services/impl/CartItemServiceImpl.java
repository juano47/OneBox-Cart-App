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
  public List<CartItem> setCartItems(final List<CartItem> items, final Cart cartDb) {
    if (items == null || items.isEmpty()) {
      return List.of();
    }
    for (CartItem item : items) {
      int quantity = item.getQuantity();
      if (item.getProduct() ==null || item.getProduct().getId() == null) {
        throw new IllegalArgumentException("Product must be set");
      }
      Product product = productRepository.getProduct(item.getProduct().getId());
      // check if the product exists
      if (product == null) {
        throw new IllegalArgumentException(
            "Product with id " + item.getProduct().getId() + " not found");
      }
      // add new items
      if (item.getId() == null) {
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

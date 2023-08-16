package com.delaiglesia.onebox_cart_app.domain.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import com.delaiglesia.onebox_cart_app.domain.entity.Product;
import com.delaiglesia.onebox_cart_app.domain.repository.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartItemServiceImplTest {

  private CartItemServiceImpl cartItemServiceImpl;

  private ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    productRepository = mock(ProductRepository.class);
    cartItemServiceImpl = new CartItemServiceImpl(productRepository);
  }

  @Test
  void shouldAddNewItemCart() {
    CartItem cartItemNew = new CartItem(null, null, 2, 0.0);
    Product product = new Product(1L, null, null, 1.5);
    cartItemNew.setProduct(product);
    List<CartItem> items = List.of(cartItemNew);

    when(productRepository.getProduct(1L)).thenReturn(product);

    List<CartItem> result = cartItemServiceImpl.setCartItems(items, null);

    assertEquals(1, result.size());
    assertEquals(1L, result.get(0).getProduct().getId());
    assertEquals(2, result.get(0).getQuantity());
    assertEquals(3, result.get(0).getPrice());

    verify(productRepository).getProduct(1L);
  }

  @Test
  void shouldUpdateItemCart() {
    CartItem cartItemNew = new CartItem(2L, null, 2, 0.0);
    Product product = new Product(1L, null, null, 1.5);
    cartItemNew.setProduct(product);
    List<CartItem> items = List.of(cartItemNew);

    Cart cart = new Cart();
    cart.setItems(items);

    when(productRepository.getProduct(1L)).thenReturn(product);

    List<CartItem> result = cartItemServiceImpl.setCartItems(items, cart);

    assertEquals(1, result.size());
    assertEquals(1L, result.get(0).getProduct().getId());
    assertEquals(2, result.get(0).getQuantity());
    assertEquals(3, result.get(0).getPrice());

    verify(productRepository).getProduct(1L);
  }

  @Test
  void shouldThrowExceptionWhenProductNotSet() {
    CartItem cartItemNew = new CartItem(null, null, 2, 0.0);
    List<CartItem> items = List.of(cartItemNew);

    when(productRepository.getProduct(1L)).thenReturn(null);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> cartItemServiceImpl.setCartItems(items, null));

    assertEquals("Product must be set", exception.getMessage());

    verifyNoInteractions(productRepository);
  }

  @Test
  void shouldThrowExceptionWhenProductIsInvalid() {
    CartItem cartItemNew = new CartItem(null, null, 2, 0.0);
    Product product = new Product(1L, null, null, 1.5);
    cartItemNew.setProduct(product);
    List<CartItem> items = List.of(cartItemNew);

    when(productRepository.getProduct(1L)).thenReturn(null);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> cartItemServiceImpl.setCartItems(items, null));

    assertEquals("Product with id 1 not found", exception.getMessage());

    verify(productRepository).getProduct(1L);
  }

  @Test
  void shouldThrowExceptionWhenCartItemIsInvalid() {
    CartItem cartItemNew = new CartItem(2L, null, 2, 0.0);
    Product product = new Product(1L, null, null, 1.5);
    cartItemNew.setProduct(product);
    List<CartItem> items = List.of(cartItemNew);

    Cart cart = new Cart();
    cart.setItems(List.of());

    when(productRepository.getProduct(1L)).thenReturn(product);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> cartItemServiceImpl.setCartItems(items, cart));

    assertEquals("Item with id 2 does not exist. Cannot update", exception.getMessage());

    verify(productRepository).getProduct(1L);
  }
}

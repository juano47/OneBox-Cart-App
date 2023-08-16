package com.delaiglesia.onebox_cart_app.domain.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.delaiglesia.onebox_cart_app.domain.entity.CartItem;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartServiceImplTest {

  private CartServiceImpl cartServiceImpl;

  @BeforeEach
  void setUp() {
    cartServiceImpl = new CartServiceImpl();
  }

  @Test
  void calculateTotalPrice() {
    List<CartItem> items =
        List.of(
            new CartItem(1L, null, 1, 1),
            new CartItem(2L, null, 2, 2),
            new CartItem(3L, null, 3, 3));
    Double result = cartServiceImpl.calculateTotalPrice(items);

    assertEquals(6, result);
  }

  @Test
  void calculateTotalPriceEmptyList() {
    List<CartItem> items = List.of();
    Double result = cartServiceImpl.calculateTotalPrice(items);

    assertEquals(0, result);
  }
}

package com.delaiglesia.onebox_cart_app.domain.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.delaiglesia.onebox_cart_app.domain.entity.Cart;
import com.delaiglesia.onebox_cart_app.domain.entity.CartStatus;
import com.delaiglesia.onebox_cart_app.domain.repository.CartRepository;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CartScheduleTaskServiceTest {

  private CartScheduleTaskService cartScheduleTaskService;

  private CartRepository cartRepository;

  @BeforeEach
  void setUp() {
    cartRepository = mock(CartRepository.class);
    cartScheduleTaskService = new CartScheduleTaskService(cartRepository);
  }

  @Test
  void updateCartToExpiredStatus_FoundCartCase() {
    List<CartStatus> statuses =
        Arrays.asList(CartStatus.EMPTY, CartStatus.ACTIVE, CartStatus.CHECKOUT);

    List<Cart> carts = Arrays.asList(new Cart(), new Cart());

    when(cartRepository.findCartsNotUpdatedInLastTenMinutes(any(LocalDateTime.class), eq(statuses)))
        .thenReturn(carts);

    cartScheduleTaskService.updateCartToExpiredStatus();

    assertEquals(CartStatus.EXPIRED, carts.get(0).getStatus());
    assertEquals(CartStatus.EXPIRED, carts.get(1).getStatus());

    verify(cartRepository)
        .findCartsNotUpdatedInLastTenMinutes(any(LocalDateTime.class), eq(statuses));
    verify(cartRepository).saveAllCarts(carts);
  }

  @Test
  void updateCartToExpiredStatus_NotFoundCartCase() {
    List<CartStatus> statuses =
        Arrays.asList(CartStatus.EMPTY, CartStatus.ACTIVE, CartStatus.CHECKOUT);

    when(cartRepository.findCartsNotUpdatedInLastTenMinutes(any(LocalDateTime.class), eq(statuses)))
        .thenReturn(List.of());

    cartScheduleTaskService.updateCartToExpiredStatus();

    verify(cartRepository)
        .findCartsNotUpdatedInLastTenMinutes(any(LocalDateTime.class), eq(statuses));
    verify(cartRepository, times(0)).saveAllCarts(any(List.class));
  }
}
